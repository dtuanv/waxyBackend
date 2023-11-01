package com.waxy.service.mapper;

import com.waxy.database.dto.BetGroupDto;
import com.waxy.database.entity.BetGroup;

public abstract class BetGroupMapper {
    public abstract BetGroup mapToEntity(BetGroupDto betGroupDto);
    public abstract BetGroupDto mapToDto(BetGroup betGroup);
}
