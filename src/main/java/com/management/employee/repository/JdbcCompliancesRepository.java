package com.management.employee.repository;

import com.management.employee.domain.Compliance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcCompliancesRepository implements CompliancesRepository {


    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcCompliancesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int add(Compliance compliance) {
        return jdbc.update("INSERT INTO compliances (REGULATION_TYPE,DETAILS,DEPARTMENT_ID) " +
                "VALUE (?,?,?)", compliance.getRegulation_type(), compliance.getDetail(), compliance.getDepartment_id());
    }

    @Override
    public int delete(int id) {
        return jdbc.update("DELETE FROM compliances WHERE COMPLIANCE_ID=?", id);
    }

    @Override
    public Compliance findById(int id) {
        return jdbc.queryForObject("SELECT COMPLIANCE_ID,REGULATION_TYPE,DETAILS,DEPARTMENT_ID,CREATION_DATE,UPDATE_DATE FROM compliances WHERE COMPLIANCE_ID=?",
                this::mapRowToCompliance,
                id);
    }

    @Override
    public Iterable<Compliance> listAll() {
        return jdbc.query("SELECT COMPLIANCE_ID,REGULATION_TYPE,DETAILS,DEPARTMENT_ID,CREATION_DATE,UPDATE_DATE FROM compliances",
                this::mapRowToCompliance);
    }

    @Override
    public Iterable<Compliance> findByDepartmentId(int id) {
        return jdbc.query("SELECT COMPLIANCE_ID,REGULATION_TYPE,DETAILS,DEPARTMENT_ID,CREATION_DATE,UPDATE_DATE FROM compliances WHERE DEPARTMENT_ID=?",
                this::mapRowToCompliance,
                id);
    }

    @Override
    public Iterable<Compliance> findByRegualtionType(String type) {
        return jdbc.query("SELECT COMPLIANCE_ID,REGULATION_TYPE,DETAILS,DEPARTMENT_ID,CREATION_DATE,UPDATE_DATE FROM compliances WHERE REGULATION_TYPE=?",
                this::mapRowToCompliance,
                type);
    }

    private Compliance mapRowToCompliance(ResultSet rs, int rowNum) throws SQLException {
        return new Compliance(
                rs.getInt("COMPLIANCE_ID"),
                rs.getString("REGULATION_TYPE"),
                rs.getString("DETAILS"),
                rs.getInt("DEPARTMENT_ID"),
                rs.getDate("CREATION_DATE"),
                rs.getDate("UPDATE_DATE"));
    }
}
