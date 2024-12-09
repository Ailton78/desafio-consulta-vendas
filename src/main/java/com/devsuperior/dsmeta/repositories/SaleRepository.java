package com.devsuperior.dsmeta.repositories;


import com.devsuperior.dsmeta.dto.SaleSummary;
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
            "AND obj.seller.name LIKE CONCAT(:name, '%') " +
            "GROUP BY obj.seller.name " +
            "ORDER BY obj.seller.name")
    List<SaleSummary> search1(LocalDate minDate, LocalDate maxDate, String name);


}
