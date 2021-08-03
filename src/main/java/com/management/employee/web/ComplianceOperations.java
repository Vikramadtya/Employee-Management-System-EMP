package com.management.employee.web;

import com.management.employee.domain.Compliance;
import com.management.employee.domain.Department;
import com.management.employee.domain.Selection;
import com.management.employee.repository.CompliancesRepository;
import com.management.employee.repository.DepartmentsRepository;
import com.management.employee.repository.RegulationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/compliances")
public class ComplianceOperations {

    private final CompliancesRepository compliancesRepository;
    private final RegulationsRepository regulationsRepository;
    private final DepartmentsRepository departmentsRepository;


    @Autowired
    public ComplianceOperations(CompliancesRepository compliancesRepository, RegulationsRepository regulationsRepository, DepartmentsRepository departmentsRepository) {
        this.compliancesRepository = compliancesRepository;
        this.regulationsRepository = regulationsRepository;
        this.departmentsRepository = departmentsRepository;
    }

    @GetMapping("/")
    public String compliances(@RequestParam(name="id", required = false) Integer id, Model model) {
        if(id != null){
            Compliance compliance = compliancesRepository.findById(id);
            model.addAttribute("Complaince",compliance);
            return "compliance status";
        }
        ArrayList<Compliance> compliances = new ArrayList<Compliance>();
        compliancesRepository.listAll().forEach((i) -> compliances.add(i));
        model.addAttribute("List", compliances);
        return "compliance page";
    }


    @GetMapping("/add")
    public String addComplianceForm(Model model) {
        ArrayList<String> regulations = new ArrayList<String>();

        List<Department> departments = new ArrayList<Department>();
        departmentsRepository.findAll().forEach(i -> departments.add(i));

        regulationsRepository.listAll().forEach((i) -> regulations.add(i.getType()));
        model.addAttribute("Regulations", regulations);
        model.addAttribute("Departments",departments);
        model.addAttribute("Add", new Compliance());
        return "add compliance";
    }

    @PostMapping("/add")
    public String addCompliance(Compliance compliance) {
        compliancesRepository.add(compliance);
        return "redirect:/compliances/";
    }

    @PostMapping ("/delete")
    public String deleteCompliance(Selection selection){
        for ( var x : selection.getSelection()){
            compliancesRepository.delete(Integer.valueOf(x));
        }
        return "redirect:/compliances/";
    }

}
