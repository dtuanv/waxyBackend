package com.waxy.service.mapper;

import com.waxy.dto.LQuestionDto;
import com.waxy.database.entity.LQuestion;

public abstract class LQuestionMapper {

    public abstract LQuestion mapToEntity(LQuestionDto lQuestionDto);
    public abstract LQuestionDto mapToDto(LQuestion lQuestion);


}
