package com.waxy.service.mapper;

import com.waxy.database.dto.WorkplanDto;
import com.waxy.database.entity.Workplan;
import com.waxy.database.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleWorkplanMapper extends WorkplanMapper{

    final StaffRepository staffRepository;

    final StaffItemMapper staffItemMapper;
    @Override
    public WorkplanDto mapToDto(Workplan workplan) {
        WorkplanDto workplanDto = new WorkplanDto();

        workplanDto.setId(workplan.getId());

        workplanDto.setWorkweek(workplan.getWorkweek());

        workplanDto.setBusinessId(workplan.getBusinessId());

        workplanDto.setNotification(workplan.getNotification());

        workplanDto.setStaffItemSet(staffRepository.findAllByWorkplan(workplan.getId()).stream().map(staffItemMapper :: mapToDto).collect(Collectors.toSet()));
        return workplanDto;
    }
}
