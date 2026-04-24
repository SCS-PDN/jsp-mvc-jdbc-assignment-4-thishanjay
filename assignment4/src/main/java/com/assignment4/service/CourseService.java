package com.assignment4.service;

import com.assignment4.dao.CourseDao;
import com.assignment4.dao.EnrollmentDao;
import com.assignment4.dao.StudentDao;
import com.assignment4.model.Course;
import com.assignment4.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseDao courseDao;
    private final EnrollmentDao enrollmentDao;
    private final StudentDao studentDao;

    public CourseService(CourseDao courseDao, EnrollmentDao enrollmentDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.enrollmentDao = enrollmentDao;
        this.studentDao = studentDao;
    }

    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    public Course findById(int courseId) {
        return courseDao.findById(courseId);
    }

    public boolean registerLoggedInStudent(String email, int courseId) {
        Student student = studentDao.findByEmail(email);
        if (student == null) {
            return false;
        }

        Course course = courseDao.findById(courseId);
        if (course == null) {
            return false;
        }

        if (!enrollmentDao.isAlreadyRegistered(student.getId(), courseId)) {
            enrollmentDao.registerStudent(student.getId(), courseId);
        }
        return true;
    }
}
