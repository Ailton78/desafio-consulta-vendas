package com.devsuperior.dsmeta.repositories;


import com.devsuperior.dsmeta.dto.SaleSummary;
import com.devsuperior.dsmeta.dto.SeleReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummary(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate " +
            "AND :maxDate " +
            "AND LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%'))" +
            "GROUP BY obj.seller.name " +
            "ORDER BY obj.seller.name")
    List<SaleSummary> search1(LocalDate minDate, LocalDate maxDate, String name);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SeleReport(obj.id, obj.date, " +
            "obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate " +
            "AND :maxDate " +
            "AND LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%'))" )
    Page<SeleReport> search2(Pageable pageable, LocalDate minDate, LocalDate maxDate, String name);



}
