package com.waxy.service.mapper;

import com.waxy.database.entity.BetGroup;
import com.waxy.dto.BetGroupDto;

public abstract class BetGroupMapper {
    public abstract BetGroup mapToEntity(BetGroupDto betGroupDto);
    public abstract BetGroupDto mapToDto(BetGroup betGroup);
}
