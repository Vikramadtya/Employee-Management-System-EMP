package com.management.employee.repository;

import com.management.employee.domain.Report;

public interface ReportsRepository {
    int add(Report report);
    int delete(int id);

    Report findById(int id);

    Iterable<Report> listAll();
    Iterable<Report> findByComplianceID(int id);
    Iterable<Report> findByEmployee(int id);
}

