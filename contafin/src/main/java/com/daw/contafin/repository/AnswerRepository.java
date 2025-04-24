package com.daw.contafin.repository;

import com.daw.contafin.entity.Answer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
	Answer findById(long Id);
}
