package com.labapp.LabApp.repository;

import com.labapp.LabApp.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByOrderByDateAsc();
    List<Report> findByOrderByDateDesc();

    @Query("select u from Report u where lower(u.patientName) like lower(concat('%', :param1, '%') )" +
            "or lower(u.patientSurname) like lower(concat('%', :param1, '%'))" +
            "or lower(u.patientID) like lower(:param1)"+
            "or lower(u.laborant.laborantId) like lower(:param1)"+
            "or lower(u.laborant.laborantName) like lower(concat('%', :param1, '%') )"+
            "or lower(u.laborant.laborantSurname) like lower(concat('%', :param1, '%') )")
    List<Report> findByNameOrLastnameOrLaborantName(@Param("param1") String param1);

    /* BU QUERY ÇOK AMA ÇOK ÖNEMLİ LÜTFEN BAK ACİL BAK ÖĞREN BURAYI BİLMİYORSUN */

}
