package com.assignment4.dao;

import org.springframework.dao.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentDaoImpl implements EnrollmentDao {

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isAlreadyRegistered(int studentId, int courseId) {
        try {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM registrations WHERE student_id = ? AND course_id = ?",
                    Integer.class,
                    studentId,
                    courseId
            );
            return count != null && count > 0;
        } catch (BadSqlGrammarException ex) {
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM enrollments WHERE student_id = ? AND course_id = ?",
                    Integer.class,
                    studentId,
                    courseId
            );
            return count != null && count > 0;
        }
    }

    @Override
    public void registerStudent(int studentId, int courseId) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO registrations(student_id, course_id) VALUES(?, ?)",
                    studentId,
                    courseId
            );
        } catch (BadSqlGrammarException ex) {
            jdbcTemplate.update(
                    "INSERT INTO enrollments(student_id, course_id) VALUES(?, ?)",
                    studentId,
                    courseId
            );
        }
    }
}
