package com.daw.contafin.unit;

import com.daw.contafin.lesson.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository <Unit, Long>{
	Unit findById(long Id);
	Page<Unit> findAll(Pageable page);
	Unit findByLessonsId(long id);
}
