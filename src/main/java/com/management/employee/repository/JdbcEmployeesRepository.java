package com.management.employee.repository;

import com.management.employee.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcEmployeesRepository implements EmployeesRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcEmployeesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Employee> findAll() {
        return jdbc.query("SELECT EMP_ID,USER_NAME,FIRST_NAME,LAST_NAME,DOB,EMAIL,DEPARTMENT_ID,CREATION_DATE,UPDATE_DATE FROM employees",
                this::mapRowToEmployee);
    }


    @Override
    public Employee find(Integer id) {
        return jdbc.queryForObject("SELECT EMP_ID,USER_NAME,FIRST_NAME,LAST_NAME,DOB,EMAIL,DEPARTMENT_ID,CREATION_DATE,UPDATE_DATE FROM employees WHERE EMP_ID=?",
                this::mapRowToEmployee, id);
    }

    @Override
    public int delete(Integer id) {
        return jdbc.update("DELETE FROM employees WHERE id=?",
                id);
    }

    @Override
    public int update(Employee emp) {
        return jdbc.update("UPDATE employees SET FIRST_NAME=?,LAST_NAME=?,DOB=?,EMAIL=?,DEPARTMENT_ID=? WHERE id=?",
                emp.getFirst_name(),
                emp.getLast_name(),
                emp.getDate_of_birth(),
                emp.getEmail(),
                emp.getDepartment_id(),
                emp.getId());
    }

    @Override
    public int add(Employee emp) {
        return jdbc.update("INSERT INTO employees (USER_NAME,FIRST_NAME,LAST_NAME,DOB,EMAIL,DEPARTMENT_ID) VALUE (?,?,?,?,?,?)",
                emp.getFirst_name().concat(emp.getDepartment_id().toString()),
                emp.getFirst_name(),
                emp.getLast_name(),
                emp.getDate_of_birth(),
                emp.getEmail(),
                emp.getDepartment_id());
    }

    private Employee mapRowToEmployee(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                rs.getInt("EMP_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getDate("DOB"),
                rs.getString("EMAIL"),
                rs.getInt("DEPARTMENT_ID"),
                rs.getDate("CREATION_DATE"),
                rs.getDate("UPDATE_DATE"));
    }

}
