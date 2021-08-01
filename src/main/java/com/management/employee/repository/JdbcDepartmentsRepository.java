package com.management.employee.repository;

import com.management.employee.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcDepartmentsRepository implements DepartmentsRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    JdbcDepartmentsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Department> findAll() {
        return jdbc.query("SELECT DEPARTMENT_ID,DEPARTMENT_NAME,CREATION_DATE,UPDATE_DATE FROM departments",
                this::mapRowToDepartment);
    }

    @Override
    public Department find(int id) {
        return jdbc.queryForObject("SELECT DEPARTMENT_ID,DEPARTMENT_NAME,CREATION_DATE,UPDATE_DATE FROM departments WHERE DEPARTMENT_ID=?",
                this::mapRowToDepartment, id);
    }

    @Override
    public int add(Department dept) {
        return jdbc.update("INSERT INTO departments (DEPARTMENT_NAME) VALUE (?)",
                dept.getName());
    }

    @Override
    public int update(Department dept) {
        return jdbc.update("UPDATE departments SET DEPARTMENT_NAME=? WHERE id=?",
                dept.getName(),
                dept.getId());
    }

    @Override
    public int delete(int id) {
        return jdbc.update("DELETE FROM departments WHERE id=?",
                id);
    }

    private Department mapRowToDepartment(ResultSet rs, int rowNum) throws SQLException {
        return new Department(rs.getInt("DEPARTMENT_ID"),
                rs.getString("DEPARTMENT_NAME"),
                rs.getDate("CREATION_DATE"),
                rs.getDate("UPDATE_DATE"));
    }
}
