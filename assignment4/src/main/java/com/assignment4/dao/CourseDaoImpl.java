package com.assignment4.dao;

import com.assignment4.model.Course;
import org.springframework.dao.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Course> courseRowMapper = (rs, rowNum) ->
            new Course(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("title"),
                    rs.getInt("credits")
            );

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> findAll() {
        try {
            return jdbcTemplate.query(
                    "SELECT id, code, title, credits FROM courses ORDER BY id",
                    courseRowMapper
            );
        } catch (BadSqlGrammarException ex) {
            return jdbcTemplate.query(
                    "SELECT course_id AS id, CAST(course_id AS CHAR) AS code, name AS title, credits FROM courses ORDER BY course_id",
                    courseRowMapper
            );
        }
    }

    @Override
    public Course findById(int courseId) {
        try {
            List<Course> courses = jdbcTemplate.query(
                    "SELECT id, code, title, credits FROM courses WHERE id = ?",
                    courseRowMapper,
                    courseId
            );
            return courses.isEmpty() ? null : courses.get(0);
        } catch (BadSqlGrammarException ex) {
            List<Course> courses = jdbcTemplate.query(
                    "SELECT course_id AS id, CAST(course_id AS CHAR) AS code, name AS title, credits FROM courses WHERE course_id = ?",
                    courseRowMapper,
                    courseId
            );
            return courses.isEmpty() ? null : courses.get(0);
        }
    }
}
