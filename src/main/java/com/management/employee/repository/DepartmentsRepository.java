package com.management.employee.repository;

import com.management.employee.domain.Department;
import org.springframework.stereotype.Repository;

public interface DepartmentsRepository {

    int add(Department dept);
    int delete(int id);
    int update(Department dept);
    Department find(int id);
    Iterable<Department> findAll();
}
