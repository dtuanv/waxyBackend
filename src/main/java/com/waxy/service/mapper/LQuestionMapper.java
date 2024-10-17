package com.waxy.service.mapper;

import com.waxy.database.entity.LQuestion;
import com.waxy.dto.LQuestionDto;

public abstract class LQuestionMapper {

    public abstract LQuestion mapToEntity(LQuestionDto lQuestionDto);
    public abstract LQuestionDto mapToDto(LQuestion lQuestion);


}
