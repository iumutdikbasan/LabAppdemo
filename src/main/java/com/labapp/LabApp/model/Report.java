package com.labapp.LabApp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;


@Entity /* Bir sınıfın veri tabanına karşılık gelmesi için*/
@Table(name = "reports")
public class Report {

    @Id /* Her tablonun bir idsi olur*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)/* Bunu farklı yapabilirsin */
    @Column(name="id")
    private int id;

    @Column(name = "patient_Name")
    private String patientName;

    @Column(name = "patient_Lastname")
    private String patientLastname;

    @Column(name = "patient_ID")
    @Min(value = 0, message = "ID shouldn't be less than 0.")
    @Max(value = 999999, message = "ID shouldn't be greater than 999999.")
    private Long patientID;

    @Column(name = "patient_Diagnosis")
    private String patientDiagnosis;/*Silinebilir*/

    @Column(name = "diagnosis_Detail")
    private String diagnosisDetail;/*Silinebilir*/

    @Column(name = "date")
    private Date date = new Date(new java.util.Date().getTime());

    @Lob
    @Column(columnDefinition = "BLOB")/*Binary Large Object is for storing binary data like image, audio, or video*/
    private byte [] repImage; /* Bunu farklı bir yolla yazmayı dene veya bunu iyi öğren burdan soru çıkabilir*/

    @Column(name = "file_ext")
    private String fileExt; /* Silinebilir */


    @ManyToOne()
    @JoinColumn(name = "laborant_id", nullable = false)
    @NotNull(message = "Please select laborant.")
    private Laborant laborant; /* Laborant rapor ilişkisi */

    public Report() {
    }
    /* DEVAMINI YAZ YARIM BIRAKTIN */

    public Report(int id,String patientName,String patientLastname, long patientID, String patientDiagnosis, String diagnosisDetail, Date date, byte[] repImage,String fileExt, Laborant laborant ){
        this.id = id;
        this.setPatientName(patientName);
        this.setPatientLastname(patientLastname);
        this.setPatientID(patientID);
        this.setPatientDiagnosis(patientDiagnosis);
        this.setDiagnosisDetail(diagnosisDetail);
        this.setDate(date);
        this.setRepImage(repImage);
        this.setFileExt(fileExt);
        this.setLaborant(laborant);
    }
    public Report(String patientName,String patientLastname, long patientID, String patientDiagnosis, String diagnosisDetail, Date date, byte[] repImage,String fileExt, Laborant laborant ){
        this.setPatientName(patientName);
        this.setPatientLastname(patientLastname);
        this.setPatientID(patientID);
        this.setPatientDiagnosis(patientDiagnosis);
        this.setDiagnosisDetail(diagnosisDetail);
        this.setDate(date);
        this.setRepImage(repImage);
        this.setFileExt(fileExt);
        this.setLaborant(laborant);
    }

    public int getId(){
        return id;
    }
    public void setId(int fileId) {
        this.id = fileId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientLastname() {
        return patientLastname;
    }

    public void setPatientLastname(String patientLastname) {
        this.patientLastname = patientLastname;
    }

    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public String getPatientDiagnosis() {
        return patientDiagnosis;
    }

    public void setPatientDiagnosis(String patientDiagnosis) {
        this.patientDiagnosis = patientDiagnosis;
    }

    public String getDiagnosisDetail() {
        return diagnosisDetail;
    }

    public void setDiagnosisDetail(String diagnosisDetail) {
        this.diagnosisDetail = diagnosisDetail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getRepImage() {
        return repImage;
    }

    public void setRepImage(byte[] repImage) {
        this.repImage = repImage;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public Laborant getLaborant() {
        return laborant;
    }

    public void setLaborant(Laborant laborant) {
        this.laborant = laborant;
    }

    /* IOException neden kullandık iyice hafızana kazı*/

    public void setRepImg(MultipartFile file) throws IOException{
        fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
        this.repImage = file.getBytes();
    }
    @Override
    public String toString() {
        return "Report{" +
                "fileId=" + id +
                ", patientName='" + patientName + '\'' +
                ", patientLastname='" + patientLastname + '\'' +
                ", patientId=" + patientID +
                ", patientDiagnosis='" + patientDiagnosis + '\'' +
                ", diagnosisDetail='" + diagnosisDetail + '\'' +
                ", date=" + date +
                ", reportImg=" + Arrays.toString(repImage) +
                ", fileExt='" + fileExt + '\'' +
                ", laborant=" + laborant +
                '}';
    }
}
