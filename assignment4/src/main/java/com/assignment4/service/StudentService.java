package com.assignment4.service;

import com.assignment4.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void createStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
}
