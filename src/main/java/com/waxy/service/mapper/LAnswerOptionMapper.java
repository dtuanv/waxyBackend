package com.waxy.service.mapper;

import com.waxy.database.dto.LAnswerOptionDto;
import com.waxy.database.entity.LAnswerOption;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class LAnswerOptionMapper {

    public abstract LAnswerOption mapToEntity(LAnswerOptionDto lAnswerOptionDto);
    public abstract LAnswerOptionDto mapToDto(LAnswerOption lAnswerOption);
}
