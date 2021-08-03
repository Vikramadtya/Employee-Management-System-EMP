package com.management.employee.web;


import com.management.employee.domain.Comment;
import com.management.employee.domain.Compliance;
import com.management.employee.domain.Report;
import com.management.employee.repository.CommentsRepository;
import com.management.employee.repository.CompliancesRepository;
import com.management.employee.repository.EmployeesRepository;
import com.management.employee.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportOperations {

    private final ReportsRepository reportsRepository;
    private final CommentsRepository commentsRepository;
    private final CompliancesRepository compliancesRepository;

    @Autowired
    public ReportOperations(ReportsRepository reportsRepository, CommentsRepository commentsRepository, CompliancesRepository compliancesRepository){
        this.reportsRepository = reportsRepository;
        this.commentsRepository = commentsRepository;
        this.compliancesRepository = compliancesRepository;
    }

    @GetMapping("/")
    public String reports(@RequestParam (name="id", required = true) int id, Model model){
        List<Report> reports = new ArrayList<Report>();
        reportsRepository.findByComplianceID(id).forEach(i -> reports.add(i));

        model.addAttribute("reports",reports);
        return "report";
    }

    @GetMapping("/status")
    public String reportStatus(@RequestParam (name="id", required = true) int id, Model model){
        Report report = reportsRepository.findById(id);

        List<Comment> comments = new ArrayList<Comment>();
        commentsRepository.findByReportId(report.getId()).forEach(i -> comments.add(i));

        Compliance compliance = compliancesRepository.findById(report.getCompliance_id());
        model.addAttribute("report",report);
        model.addAttribute("comments",comments);
        model.addAttribute("compliance",compliance);
        return "report status";
    }
}
