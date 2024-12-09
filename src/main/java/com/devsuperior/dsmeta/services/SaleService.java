package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	private LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	private DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}


	public List<SaleSummary> getSummary(String minDate, String maxDate , String name){
		String nome = name;
		LocalDate dataFim = maxDate.isBlank()? today: LocalDate.parse(maxDate, fmt);
		LocalDate dataInicio = minDate.isBlank() ? dataFim.minusYears(1L) : LocalDate.parse(minDate, fmt);
		List<SaleSummary> dto = repository.search1(dataInicio, dataFim, nome);
		return dto;
	}

}
