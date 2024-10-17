package com.waxy.service.mapper;

import com.waxy.database.entity.Workplan;
import com.waxy.dto.WorkplanDto;

public abstract class WorkplanMapper {

    public abstract WorkplanDto mapToDto(Workplan workplan);
}
