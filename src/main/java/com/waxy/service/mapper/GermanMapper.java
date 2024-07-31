package com.waxy.service.mapper;

import com.waxy.dto.GermanDto;
import com.waxy.database.entity.German;

public abstract class GermanMapper {
    public abstract German mapToEntity(GermanDto germanDto);

    public abstract GermanDto mapToDto(German german);
}
