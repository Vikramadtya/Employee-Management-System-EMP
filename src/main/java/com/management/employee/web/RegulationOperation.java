package com.management.employee.web;

import com.management.employee.domain.Department;
import com.management.employee.domain.Regulation;
import com.management.employee.domain.Selection;
import com.management.employee.repository.DepartmentsRepository;
import com.management.employee.repository.RegulationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/regulation")
public class RegulationOperation {

    private List<Regulation> regulations;
    private RegulationsRepository regulationsRepository;

    @Autowired
    public RegulationOperation(RegulationsRepository regulationsRepository){
        this.regulationsRepository = regulationsRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        if(regulations == null){
            regulations = new ArrayList<Regulation>();
            regulationsRepository.listAll().forEach(i -> regulations.add(i));
        }
        model.addAttribute("Regulations",regulations);
    }

    @GetMapping("/")
    public String regulationsPage(Model model){
        model.addAttribute("Add",new Regulation());
        model.addAttribute("Select",new Selection());
        return "regulation page";
    }

    @PostMapping("/add")
    public String addRegulations(Regulation regulation){
        regulations = null;
        regulationsRepository.add(regulation);
        return "redirect:/regulation/";
    }

    @PostMapping("/delete")
    public String deleteRegualtion(Selection selection){
        regulations = null;
        for( var x : selection.getSelection()){
            regulationsRepository.delete(x);
        }
        return "redirect:/regulation/";
    }
}
