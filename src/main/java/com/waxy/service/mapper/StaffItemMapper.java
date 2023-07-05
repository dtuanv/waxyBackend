package com.waxy.service.mapper;

import com.waxy.database.dto.StaffItemDto;
import com.waxy.database.entity.StaffItem;

public abstract class StaffItemMapper {
    public abstract StaffItemDto mapToDto(StaffItem staffItem);
}
