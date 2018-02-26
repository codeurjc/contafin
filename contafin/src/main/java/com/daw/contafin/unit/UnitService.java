package com.daw.contafin.unit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
