package com.daw.contafin.answer;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AnswerMapper {

    AnswerDto AnswerToAnswerDto(Answer answer);
    Answer AnswerDtoToAnswer(AnswerDto answerDto);

    List<AnswerDto> AnswersToAnswersDto(Collection<Answer> answers);
    List<Answer> AnswersDtoToAnswers(Collection<AnswerDto> answersDto);
}
