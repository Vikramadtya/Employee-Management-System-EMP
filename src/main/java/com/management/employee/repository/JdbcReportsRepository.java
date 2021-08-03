package com.management.employee.repository;

import com.management.employee.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcReportsRepository implements ReportsRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcReportsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public int add(Report report) {
        return jdbc.update("INSERT INTO reports (COMPLIANCE_ID,EMP_ID) VALUE (?,?)",
                report.getCompliance_id(),
                report.getEmployee_id());
    }

    @Override
    public int delete(int id) {
        return jdbc.update("DELETE FROM reports WHERE REPORT_ID=?",
                id);
    }

    @Override
    public Report findById(int id) {
        return jdbc.queryForObject("SELECT REPORT_ID,COMPLIANCE_ID,EMP_ID,CREATION_DATE,UPDATE_DATE FROM reports WHERE REPORT_ID=?",
                this::mapRowToReport, id);
    }

    @Override
    public Iterable<Report> listAll() {
        return jdbc.query("SELECT REPORT_ID,COMPLIANCE_ID,EMP_ID,CREATION_DATE,UPDATE_DATE FROM reports ",
                this::mapRowToReport);
    }

    @Override
    public Iterable<Report> findByComplianceID(int id) {
        return jdbc.query("SELECT REPORT_ID,COMPLIANCE_ID,EMP_ID,CREATION_DATE,UPDATE_DATE FROM reports WHERE COMPLIANCE_ID=?",
                this::mapRowToReport, id);
    }

    @Override
    public Iterable<Report> findByEmployee(int id) {
        return jdbc.query("SELECT REPORT_ID,COMPLIANCE_ID,EMP_ID,CREATION_DATE,UPDATE_DATE FROM reports WHERE EMP_ID=?",
                this::mapRowToReport, id);
    }

    private Report mapRowToReport(ResultSet rs, int RowNum) throws SQLException {
        return new Report(rs.getInt("REPORT_ID"), rs.getInt("COMPLIANCE_ID"), rs.getInt("EMP_ID"),
                rs.getDate("CREATION_DATE"),
                rs.getDate("UPDATE_DATE"));
    }
}
