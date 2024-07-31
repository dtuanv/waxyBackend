package com.waxy.service.mapper;

import com.waxy.dto.LAnswerOptionDto;
import com.waxy.database.entity.LAnswerOption;

public abstract class LAnswerOptionMapper {

    public abstract LAnswerOption mapToEntity(LAnswerOptionDto lAnswerOptionDto);
    public abstract LAnswerOptionDto mapToDto(LAnswerOption lAnswerOption);
}
