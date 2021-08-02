package com.management.employee.web;

import com.management.employee.domain.Department;
import com.management.employee.domain.Employee;
import com.management.employee.domain.Selection;
import com.management.employee.repository.DepartmentsRepository;
import com.management.employee.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentOperation {

    private DepartmentsRepository departmentsRepository;
    private List<Department> departments;


    @Autowired
    public DepartmentOperation(DepartmentsRepository departmentsRepository){
        this.departmentsRepository = departmentsRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        if(departments == null){
            departments = new ArrayList<Department>();
            departmentsRepository.findAll().forEach(i -> departments.add(i));
        }
        model.addAttribute("Departments",departments);
    }

    @GetMapping("/")
    public String departments(Model model){
        model.addAttribute("Select",new Selection());
        model.addAttribute("Add",new Department());
        return "department page";
    }

    @PostMapping("/add")
    public String addDepartment(@Valid  @ModelAttribute("Add") Department dept, Errors errors){
        if (errors.hasErrors())
            return "redirect:/add";

        departments = null;
        departmentsRepository.add(dept);
        return "department page";
    }

    @PostMapping("/delete")
    public String addDepartment(Selection dept){

        departments = null;
        for (var x : dept.getSelection())
            departmentsRepository.delete(Integer.valueOf(x));
        return "redirect:/department";
    }
}
