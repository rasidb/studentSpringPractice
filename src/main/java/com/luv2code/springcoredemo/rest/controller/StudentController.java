package com.luv2code.springcoredemo.rest.controller;

import com.luv2code.springcoredemo.dao.StudentDAO;
import com.luv2code.springcoredemo.entity.Student;
import com.luv2code.springcoredemo.rest.exception.StudentBadRequestException;
import com.luv2code.springcoredemo.rest.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentDAO studentDAO;

    @Autowired
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @DeleteMapping("/students/delete/{pathId}")
    public String deleteStudent(@PathVariable String pathId){
        int id;
        try {
            id=Integer.parseInt(pathId);
        }catch (NumberFormatException e){
            throw new StudentBadRequestException("bad request: "+ pathId);
        }
        if (studentDAO.getAllId().contains(id)){
            studentDAO.removeStudent(id);
        }else {
            throw new StudentNotFoundException("student not found: "+ pathId);
        }
        return "student was deleted";
    }

    @PostMapping(value = "/students/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (student.getEmail() == null || student.getFirstName() == null || student.getLastName() == null){
            throw new StudentBadRequestException("first name, last name, email alanları boş bırakılamaz");
        }else {
            studentDAO.createStudent(student);
        }
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    @GetMapping(value = "/students/{id}")
    public Student getStudentPath(@PathVariable String id) {
        int value;
        try {
            value = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new StudentBadRequestException("Bad Request " + id);
        }
        if (!studentDAO.getAllId().contains(value)) {
            throw new StudentNotFoundException("student not found: " + value);
        }
        return studentDAO.findById(value).get(0);
    }


    @GetMapping(value = "/students")
    public List<Student> getStudents(@RequestParam(required = false) String firstName, @RequestParam(required = false) Integer id) {
        if (firstName == null && id == null)
            return studentDAO.findAll();
        if (firstName != null && id == null) {
            List<Student> byName = studentDAO.findByName(firstName);
            if (byName.isEmpty()) throw new StudentNotFoundException("student not found with name: " + firstName);
            else return byName;
        }
        if (firstName == null)
            return studentDAO.findById(id);
        return studentDAO.findByIdAndLastName(firstName,id);
    }

}
