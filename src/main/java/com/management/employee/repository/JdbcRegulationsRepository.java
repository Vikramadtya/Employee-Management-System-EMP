package com.management.employee.repository;

import com.management.employee.domain.Employee;
import com.management.employee.domain.Regulation;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcRegulationsRepository implements RegulationsRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcRegulationsRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public int add(Regulation regulation) {
        return jdbc.update("INSERT INTO regulations (REGULATION_TYPE) VALUE (?)",
                regulation.getType());
    }

    @Override
    public int delete(String type) {
        return jdbc.update("DELETE FROM regulations WHERE REGULATION_TYPE=?",
                type);
    }

    @Override
    public Iterable<Regulation> listAll() {
        return jdbc.query("SELECT REGULATION_TYPE,CREATION_DATE,UPDATE_DATE FROM regulations",
                this::mapRowToRegulation);
    }

    private Regulation mapRowToRegulation(ResultSet rs, int rowNum) throws SQLException {
        return new Regulation(
                rs.getString("REGULATION_TYPE"),
                rs.getDate("CREATION_DATE"),
                rs.getDate("UPDATE_DATE"));
    }

}
