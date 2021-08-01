package com.management.employee.repository;

import com.management.employee.domain.Employee;

public interface EmployeesRepository {
    Iterable<Employee> findAll();
    Employee find(Integer id);
    int delete(Integer id);
    int update(Employee emp);
    int add(Employee emp);
}
