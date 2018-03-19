package com.daw.contafin.unit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UnitService {

	@Autowired
	UnitRepository unitRepository;
	
	public List<Unit> findAll(){
		return unitRepository.findAll();
	}
	
	public void save(Unit unit){
		unitRepository.save(unit);
	}
	
	public Unit findById(long Id) {
		return unitRepository.findById(Id);
	}
	
	public void delete(long Id) {
		unitRepository.delete(Id);
	}
	
	public Page<Unit> getUnits(Pageable page){
		return unitRepository.findAll(page);
	}
}
