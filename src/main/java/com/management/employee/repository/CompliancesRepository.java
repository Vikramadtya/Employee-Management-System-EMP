package com.management.employee.repository;

import com.management.employee.domain.Compliance;

public interface CompliancesRepository {
    int add(Compliance compliance);
    int delete(int id);
    Compliance findById(int id);
    Iterable<Compliance> listAll();
    Iterable<Compliance> findByDepartmentId(int id);
    Iterable<Compliance> findByRegualtionType(String type);
}
