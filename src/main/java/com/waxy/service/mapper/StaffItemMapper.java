package com.waxy.service.mapper;

import com.waxy.database.entity.StaffItem;
import com.waxy.dto.StaffItemDto;

public abstract class StaffItemMapper {
    public abstract StaffItemDto mapToDto(StaffItem staffItem);
}
