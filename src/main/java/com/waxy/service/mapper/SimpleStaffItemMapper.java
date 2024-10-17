package com.waxy.service.mapper;

import com.waxy.database.entity.StaffItem;
import com.waxy.dto.StaffItemDto;
import org.springframework.stereotype.Service;

@Service
public class SimpleStaffItemMapper extends StaffItemMapper{
    @Override
    public StaffItemDto mapToDto(StaffItem staffItem) {
        StaffItemDto staffItemDto = new StaffItemDto();

        staffItemDto.setStaffId(staffItem.getStaffId());

        staffItemDto.setIndexDay(staffItem.getIndexDay());

        staffItemDto.setTitle(staffItem.getTitle());

        staffItemDto.setId(staffItem.getId());

        staffItemDto.setTime(staffItem.getTime());

        staffItemDto.setDepartment(staffItem.getDepartment());
        return staffItemDto;
    }
}
