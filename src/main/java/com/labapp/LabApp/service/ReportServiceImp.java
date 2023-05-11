package com.labapp.LabApp.service;

import com.labapp.LabApp.model.Report;
import com.labapp.LabApp.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImp implements ReportService{
    private ReportRepository reportRepository;

    @Autowired
    public ReportServiceImp(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> findAll(){
        return reportRepository.findAll();
    }
    @Override
    public List<Report> orderByAsc(){
        return reportRepository.findByOrderByDateAsc();
    }
    @Override
    public List<Report> orderByDesc(){
        return reportRepository.findByOrderByDateDesc();
    }

    @Override/* Burası çokemelli*/
    public Report findById(int theId){
        Optional<Report> result = reportRepository.findById(theId);
        Report report = null;
        if (result.isPresent()) report = result.get();
        else throw new RuntimeException("False ID. "+ theId);
        return report;
    }
    @Override
    public Report save(Report theReport){
        return reportRepository.save(theReport);
    }
    @Override
    public void deleteById(int theId) {
        reportRepository.deleteById(theId);
    }
    @Override/* Burası çokemelli*/
    public List<Report> searchBy(String param){
        List<Report> results= null;
        if (param != null && (param.trim().length() > 0)){
            results = reportRepository.findByNameOrLastnameOrLaborantName(param);
        }
        else if (param.trim().length() == 0)
        {
            results = reportRepository.findAll();
        }
        return results;
    }
}

