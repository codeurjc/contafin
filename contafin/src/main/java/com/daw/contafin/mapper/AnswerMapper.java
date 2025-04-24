package com.daw.contafin.mapper;

import com.daw.contafin.dto.AnswerDto;
import com.daw.contafin.entity.Answer;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AnswerMapper {

    AnswerDto AnswerToAnswerDto(Answer answer);
    Answer AnswerDtoToAnswer(AnswerDto answerDto);
}
