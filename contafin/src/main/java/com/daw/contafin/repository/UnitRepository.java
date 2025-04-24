package com.daw.contafin.repository;

import com.daw.contafin.entity.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {
	Unit findById(long Id);
	Unit findByLessonsId(long id);
}
