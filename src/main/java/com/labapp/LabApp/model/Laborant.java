package com.labapp.LabApp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "laborant")
public class Laborant {

    @Id
    @Column(name = "laborant_id")
    @Min(value = 0, message = "ID shouldn't be less than 0.")
    @Max(value = 999999, message = "ID shouldn't be greater than 999999.")
    private Integer laborantId;

    @Column(name = "laborant_name")
    @NotBlank(message = "Please enter a name.")
    private String laborantName;

    @Column(name = "laborant_name")
    @NotBlank(message = "Please enter a surname.")
    private String laborantSurname;

    @OneToMany(mappedBy = "laborant")
    private List<Report> reports;

    /* DEVAMINI YAZ YARIM BIRAKTIN */

    public Laborant(){

    }
    public Laborant(Integer laborantId, String laborantName, String laborantSurname, List<Report> reports){
        this.setLaborantId(laborantId);
        this.setLaborantName(laborantName);
        this.setLaborantSurname(laborantSurname);
        this.setReports(reports);
    }
    public  Laborant(String laborantName,String laborantSurname, List<Report> reports){
        this.setLaborantName(laborantName);
        this.setLaborantSurname(laborantSurname);
        this.setReports(reports);
    }


    public Integer getLaborantId() {
        return laborantId;
    }

    public void setLaborantId(Integer laborantId) {
        this.laborantId = laborantId;
    }

    public String getLaborantName() {
        return laborantName;
    }

    public void setLaborantName(String laborantName) {
        this.laborantName = laborantName;
    }

    public String getLaborantSurname() {
        return laborantSurname;
    }

    public void setLaborantSurname(String laborantSurname) {
        this.laborantSurname = laborantSurname;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
    @Override /*Buraya dikkat et neden yazdığımızı anla çok önemli bilmiyorsun*/
    public String toString(){
        return "Laborant{"+ "laborantID="+ getLaborantId() + ", laborantName="+ getLaborantName() +'\''+ ", laborantSurname="+ getLaborantSurname() +'\''+ '}';

    }
}
