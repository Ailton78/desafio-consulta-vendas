package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSummary;
import com.devsuperior.dsmeta.dto.SeleReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	private LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	private DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}


	@Transactional(readOnly = true)
	public List<SaleSummary> getSummary(String minDate, String maxDate , String name){
		LocalDate dataFim;
		LocalDate dataInicio;
		if(maxDate != null && !maxDate.isBlank()){
			dataFim = LocalDate.parse(maxDate, fmt);
		}else {
			dataFim = today;
		}

		if(minDate != null && !minDate.isBlank()){
			dataInicio = LocalDate.parse(minDate, fmt);
		}else {
			dataInicio = dataFim.minusYears(1L);
		}
		List<SaleSummary> list = repository.search1(dataInicio, dataFim, name);
		return list;
	}
	@Transactional(readOnly = true)
	public Page<SeleReport> getReport(Pageable pageable, String minDate, String maxDate , String name){
		LocalDate dataFim;
		LocalDate dataInicio;
		if(maxDate != null && !maxDate.isBlank()){
			dataFim = LocalDate.parse(maxDate, fmt);
		}else {
			dataFim = today;
		}

		if(minDate != null && !minDate.isBlank()){
			dataInicio = LocalDate.parse(minDate, fmt);
		}else {
			dataInicio = dataFim.minusYears(1L);
		}
		Page<SeleReport> list = repository.search2(pageable, dataInicio, dataFim, name);
		return list;
	}



}
