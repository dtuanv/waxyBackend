package com.waxy.service.mapper;

import com.waxy.database.dto.LQuestionDto;
import com.waxy.database.entity.LQuestion;
import com.waxy.database.repository.LQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class LQuestionMapper {

    public abstract LQuestion mapToEntity(LQuestionDto lQuestionDto);
    public abstract LQuestionDto mapToDto(LQuestion lQuestion);


}
