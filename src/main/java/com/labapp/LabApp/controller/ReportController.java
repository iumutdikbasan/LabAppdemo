package com.labapp.LabApp.controller;


import com.labapp.LabApp.model.Laborant;
import com.labapp.LabApp.model.Report;
import com.labapp.LabApp.service.LaborantService;
import com.labapp.LabApp.service.ReportService;
import jakarta.validation.Valid;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {
    private ReportService reportService;
    private LaborantService laborantService;
    private boolean sortDirFlag = true;

    @Autowired
    public ReportController(ReportService theReportService, LaborantService theLaborantService){
        reportService = theReportService;
        laborantService = theLaborantService;
    }
    @GetMapping("/list")
    public String listReports(Model theModel){
        List<Report> reports;
        if(sortDirFlag)
        {
            reports = reportService.orderByAsc();
            sortDirFlag = false;
        }
        else
        {
            reports = reportService.orderByDesc();
            sortDirFlag = true;
        }

        theModel.addAttribute("reports", reports);
        return "list-reports";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Report report = new Report();
        List<Laborant> laborants = laborantService.findAll();

        theModel.addAttribute("report", report);
        theModel.addAttribute("laborants", laborants);

        return "form-reports";
    }
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("report")Report report ,BindingResult bindingResult, Model theModel){
        theModel.addAttribute("laborant", laborantService.findAll());
        if (bindingResult.hasErrors()) return "form-reports";
        reportService.save(report);
        return "redirect:/reports/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("reportID") int reportID){
        Report report = reportService.findById(reportID);

        if (report == null) throw new RuntimeException("Wrong ID " + reportID);

        reportService.deleteById(reportID);

        return "redirect:/reports/list";
    }
    @GetMapping("/download")
    public ResponseEntity<?> download(@RequestParam("reportId") int reportId){
        Report report = reportService.findById(reportId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.getPatientName() + "\"." + report.getFileExt())
                .body(new ByteArrayResource(report.getRepImage()));
    }
    @GetMapping("/search")
    public String delete(@RequestParam("reportParam") String param, Model theModel)
    {
        List<Report> reports = reportService.searchBy(param);

        theModel.addAttribute("reports", reports);

        return "list-reports";
    }
    @GetMapping("/showFormForAddLaborant")
    public String showFormForAddLaborant(Model theModel)
    {
        Laborant laborant = new Laborant();

        theModel.addAttribute("laborant", laborant);

        return "form-workers";
    }
    @PostMapping("/saveWorker")
    public String saveLaborant(@Valid @ModelAttribute("laborant") Laborant laborant, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "form-laborants";
        laborantService.save(laborant);

        return "redirect:/reports/list";
    }
}
