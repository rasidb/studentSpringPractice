package com.luv2code.springcoredemo.dao;

import com.luv2code.springcoredemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);
    void removeStudent(int id);

    int deleteAll();

    int deleteByLastname(String lastname);
    List<Integer> getAllId();
}
