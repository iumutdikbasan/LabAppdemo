package com.labapp.LabApp.service;

import com.labapp.LabApp.model.Laborant;
import com.labapp.LabApp.model.Report;

import java.util.List;

public interface LaborantService {
    List<Laborant> findAll();
    Laborant findById(int theId);
    void save(Laborant theWorker);
    void deleteById(int theId);
}

/* Değiştirilebilir */
