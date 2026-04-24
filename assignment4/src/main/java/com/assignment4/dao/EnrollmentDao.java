package com.assignment4.dao;

public interface EnrollmentDao {
    boolean isAlreadyRegistered(int studentId, int courseId);
    void registerStudent(int studentId, int courseId);
}
