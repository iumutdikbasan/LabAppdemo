package com.labapp.LabApp.service;

import com.labapp.LabApp.model.Report;

import java.util.List;

public interface ReportService {
    List<Report> findAll();
    Report findById(int theId);
    Report save(Report theReport);
    void deleteById(int theId);
    List<Report> searchBy(String param);
    List<Report> orderByAsc();
    List<Report> orderByDesc();
}
