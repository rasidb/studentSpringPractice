package com.luv2code.springcoredemo.dao;

import com.luv2code.springcoredemo.entity.Student;

import java.util.List;

public interface StudentDAO {
void save(Student theStudent);
Student findById(Integer id);
List<Student> findAll();
}
