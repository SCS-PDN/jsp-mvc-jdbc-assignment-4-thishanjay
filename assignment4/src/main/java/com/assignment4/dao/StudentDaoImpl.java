package com.assignment4.dao;

import com.assignment4.model.Student;
import org.springframework.dao.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> studentRowMapper = (rs, rowNum) ->
            new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"));

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        try {
            return jdbcTemplate.query("SELECT id, name, email FROM students ORDER BY id DESC", studentRowMapper);
        } catch (BadSqlGrammarException ex) {
            return jdbcTemplate.query("SELECT student_id AS id, name, email FROM students ORDER BY student_id DESC", studentRowMapper);
        }
    }

    @Override
    public Student findById(int id) {
        try {
            List<Student> students = jdbcTemplate.query(
                    "SELECT id, name, email FROM students WHERE id = ?",
                    studentRowMapper,
                    id
            );
            return students.isEmpty() ? null : students.get(0);
        } catch (BadSqlGrammarException ex) {
            List<Student> students = jdbcTemplate.query(
                    "SELECT student_id AS id, name, email FROM students WHERE student_id = ?",
                    studentRowMapper,
                    id
            );
            return students.isEmpty() ? null : students.get(0);
        }
    }

    @Override
    public Student findByEmail(String email) {
        try {
            List<Student> students = jdbcTemplate.query(
                    "SELECT id, name, email FROM students WHERE email = ?",
                    studentRowMapper,
                    email
            );
            return students.isEmpty() ? null : students.get(0);
        } catch (BadSqlGrammarException ex) {
            List<Student> students = jdbcTemplate.query(
                    "SELECT student_id AS id, name, email FROM students WHERE email = ?",
                    studentRowMapper,
                    email
            );
            return students.isEmpty() ? null : students.get(0);
        }
    }

    @Override
    public void save(Student student) {
        jdbcTemplate.update(
                "INSERT INTO students(name, email) VALUES(?, ?)",
                student.getName(),
                student.getEmail()
        );
    }

    @Override
    public void update(Student student) {
        try {
            jdbcTemplate.update(
                    "UPDATE students SET name = ?, email = ? WHERE id = ?",
                    student.getName(),
                    student.getEmail(),
                    student.getId()
            );
        } catch (BadSqlGrammarException ex) {
            jdbcTemplate.update(
                    "UPDATE students SET name = ?, email = ? WHERE student_id = ?",
                    student.getName(),
                    student.getEmail(),
                    student.getId()
            );
        }
    }

    @Override
    public void delete(int id) {
        try {
            jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
        } catch (BadSqlGrammarException ex) {
            jdbcTemplate.update("DELETE FROM students WHERE student_id = ?", id);
        }
    }
}
