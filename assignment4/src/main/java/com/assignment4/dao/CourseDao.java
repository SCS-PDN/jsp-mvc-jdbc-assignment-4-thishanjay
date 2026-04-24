package com.assignment4.dao;

import com.assignment4.model.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findAll();
    Course findById(int courseId);
}
