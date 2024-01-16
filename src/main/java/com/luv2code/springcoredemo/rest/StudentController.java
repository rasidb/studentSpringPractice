package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.dao.StudentDAO;
import com.luv2code.springcoredemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class StudentController {
    private StudentDAO studentDAO;

    @Autowired
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    List<Student> students;

    @PostConstruct //@PostConstruct, tek sefer çalışır
    public void loadStudentsData() {
        students = new ArrayList<>();
        students.add(new Student("firstname", "lastname", "email"));
        students.add(new Student("patates2", "dls", "456789"));
        students.add(new Student("patates3", "dls", "456789"));
    }


    @GetMapping(value = "/students/{id}")
    public Student getStudentPath(@PathVariable int id) {
        List<Integer> allId = studentDAO.getAllId();
        Collections.sort(allId);

        int index = Collections.binarySearch(allId, id);

        if (index < 0 || index >= allId.size()) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }

        return studentDAO.findById(id);
    }



    @GetMapping(value = "/students")
    public List<Student> getStudents() {
        return students;
    }


    //add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handlerException(StudentNotFoundException e) {
//create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
@ExceptionHandler
    public ResponseEntity<StudentErrorResponse> otherException(Exception e){
    //create a StudentErrorResponse
    StudentErrorResponse error = new StudentErrorResponse();
    error.setStatusCode(HttpStatus.BAD_REQUEST.value());
    error.setMessage(e.getMessage());
    error.setTimeStamp(System.currentTimeMillis());
    //return ResponseEntity
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}
}
