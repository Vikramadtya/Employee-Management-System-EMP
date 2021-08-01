package com.management.employee.web;

import com.management.employee.domain.Selection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import com.management.employee.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RequestMapping("/employees")
@Controller
@Slf4j
public class EmployeeOperation {
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("Departments",Employee.Department.values());
    }

    @GetMapping("/")
    public String employees(Model model) {
        List<Employee> Employees = Arrays.asList(new Employee(123, "3", "r", "e", "e", Employee.Department.FOUR), new Employee(23, "3", "r", "e", "e", Employee.Department.FOUR), new Employee(3, "3", "r", "e", "e", Employee.Department.FOUR), new Employee(1234, "3", "r", "e", "e", Employee.Department.FOUR), new Employee(124, "3", "r", "e", "e", Employee.Department.FOUR));


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
        return "redirect:/employees/";
    }

    @PostMapping("delete")
    public String deleteEmployee(Selection emp){
        log.info(emp.getSelection().toString());
        return "redirect:/employees/";
    }
}
