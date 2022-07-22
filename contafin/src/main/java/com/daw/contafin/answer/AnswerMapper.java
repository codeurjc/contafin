package com.daw.contafin.answer;

import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring")
public interface AnswerMapper {

    AnswerDto AnswerDtoToAnswer(Answer answer);
    Answer AnswerToAnswerDto(AnswerDto answerDto);

    List<AnswerDto> AnswersDtoToAnswers(Collection<Answer> answers);
    List<Answer> AnswersToAnswersDto(Collection<AnswerDto> answersDto);
}
