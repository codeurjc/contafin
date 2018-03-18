package com.daw.contafin.unit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UnitRepository extends JpaRepository <Unit, Long>{
	Unit findById(long Id);
	Page<Unit> findAll(Pageable page);
}
