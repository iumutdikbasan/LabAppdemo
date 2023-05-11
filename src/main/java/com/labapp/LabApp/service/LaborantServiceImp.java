package com.labapp.LabApp.service;

import com.labapp.LabApp.model.Laborant;
import com.labapp.LabApp.repository.LaborantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaborantServiceImp implements LaborantService
{
    private LaborantRepository laborantRepository;

    @Autowired
    public LaborantServiceImp(LaborantRepository laborantRepository)
    {
        this.laborantRepository = laborantRepository;
    }
    public List<Laborant> findAll() {
        return laborantRepository.findAll();
    }
    @Override
    public Laborant findById(int theId) {
        Optional<Laborant> result = laborantRepository.findById(theId);

        Laborant laborant = null;

        if (result.isPresent()) laborant = result.get();
        else throw new RuntimeException("False ID." + theId);
        return laborant;
    }
    @Override
    public void save(Laborant theLaborant) {
        laborantRepository.save(theLaborant);
    }

    @Override
    public void deleteById(int theId) {
        laborantRepository.deleteById(theId);
    }
}