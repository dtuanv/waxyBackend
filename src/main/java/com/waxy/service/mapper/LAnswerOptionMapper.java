package com.waxy.service.mapper;

import com.waxy.database.entity.LAnswerOption;
import com.waxy.dto.LAnswerOptionDto;

public abstract class LAnswerOptionMapper {

    public abstract LAnswerOption mapToEntity(LAnswerOptionDto lAnswerOptionDto);
    public abstract LAnswerOptionDto mapToDto(LAnswerOption lAnswerOption);
}
