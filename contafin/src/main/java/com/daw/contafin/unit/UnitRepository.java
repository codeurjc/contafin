package com.daw.contafin.unit;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UnitRepository extends JpaRepository <Unit, Long>{
	Unit findById(long Id);
}
