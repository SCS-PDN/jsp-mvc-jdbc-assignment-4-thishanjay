package com.assignment4.dao;

import com.assignment4.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    Student findById(int id);
    Student findByEmail(String email);
    void save(Student student);
    void update(Student student);
    void delete(int id);
}
