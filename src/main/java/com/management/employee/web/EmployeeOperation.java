package com.management.employee.web;

import com.management.employee.domain.Department;
import com.management.employee.domain.Selection;
import com.management.employee.repository.DepartmentsRepository;
import com.management.employee.repository.EmployeesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.management.employee.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RequestMapping("/employees")
@Controller
@Slf4j
public class EmployeeOperation {

    private final EmployeesRepository employeesRepository;
    private DepartmentsRepository departmentsRepository;

    private List<Integer> departments;


    @Autowired
    public EmployeeOperation(EmployeesRepository employeesRepository,DepartmentsRepository departmentsRepository){
        this.employeesRepository = employeesRepository;
        this.departmentsRepository = departmentsRepository;
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        if(departments == null){
            departments = new ArrayList<Integer>();
            departmentsRepository.findAll().forEach(i -> departments.add(i.getId()));
        }
        model.addAttribute("Departments",departments);
    }

    @GetMapping("/")
    public String employees(Model model) {
        List<Employee> Employees = new ArrayList<Employee>();
        employeesRepository.findAll().forEach(i->Employees.add(i));

        model.addAttribute("List",Employees);
        model.addAttribute("Select", new Selection());
        return "employee page";
    }

    @GetMapping("add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("Add", new Employee());
        return "add employee";
    }

    @PostMapping("add")
    public String addEmployee(@Valid @ModelAttribute("Add") Employee emp, Errors errors) {
        if(errors.hasErrors()){
            return "add employee";
        }
        log.info(String.valueOf(emp));
        employeesRepository.add(emp);
        return "redirect:/employees/";
    }

    @PostMapping("delete")
    public String deleteEmployee(Selection emp){
        log.info(emp.getSelection().toString());
        for ( var x: emp.getSelection()){
            employeesRepository.delete(Integer.valueOf(x));
        }
        return "redirect:/employees/";
    }
}
