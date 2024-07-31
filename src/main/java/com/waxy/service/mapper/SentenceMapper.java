package com.waxy.service.mapper;

import com.waxy.dto.SentenceDto;
import com.waxy.database.entity.Sentence;

public abstract class SentenceMapper {
    public abstract Sentence mapToEntity(SentenceDto sentenceDto);

    public abstract SentenceDto mapToDto(Sentence sentence);
}
