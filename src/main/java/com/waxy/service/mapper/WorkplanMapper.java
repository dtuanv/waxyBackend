package com.waxy.service.mapper;

import com.waxy.database.dto.WorkplanDto;
import com.waxy.database.entity.Workplan;

public abstract class WorkplanMapper {

    public abstract WorkplanDto mapToDto(Workplan workplan);
}
