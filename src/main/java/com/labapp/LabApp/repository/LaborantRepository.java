package com.labapp.LabApp.repository;

import com.labapp.LabApp.model.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.labapp.LabApp.model.Laborant;


public interface LaborantRepository extends JpaRepository<Laborant, Integer>
{
}

