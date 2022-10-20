package com.daw.contafin.unit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UnitRepository extends JpaRepository <Unit, Long>{
	Unit findById(long Id);
	Page<Unit> findAll(Pageable page);
}

