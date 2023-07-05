package com.waxy.service.mapper;

import com.waxy.database.dto.AgendaDto;
import com.waxy.database.dto.StaffItemDto;
import com.waxy.database.dto.WorkplanDto;
import com.waxy.database.entity.Agenda;
import com.waxy.database.entity.Workplan;
import com.waxy.database.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        List<StaffItemDto> staffItemDtos = staffRepository.findAllByWorkplan(workplan.getId()).stream().map(staffItemMapper :: mapToDto).collect(Collectors.toList());

        workplanDto.setStaffItemSet(staffItemDtos);
        List<StaffItemDto> agendaMon = new ArrayList<>();
        List<StaffItemDto> agendaTue = new ArrayList<>();
        List<StaffItemDto> agendaWed = new ArrayList<>();
        List<StaffItemDto> agendaThu= new ArrayList<>();
        List<StaffItemDto> agendaFri = new ArrayList<>();
        List<StaffItemDto> agendaSat = new ArrayList<>();
        List<StaffItemDto> agendaSun = new ArrayList<>();
        for (StaffItemDto staffItemDto : staffItemDtos) {
            if(staffItemDto.getIndexDay() == 1){
                agendaMon.add(staffItemDto);
            }
            if(staffItemDto.getIndexDay() == 2){
                agendaTue.add(staffItemDto);
            }
            if(staffItemDto.getIndexDay() == 3){
                agendaWed.add(staffItemDto);
            }

            if(staffItemDto.getIndexDay() == 4){
                agendaThu.add(staffItemDto);
            }

            if(staffItemDto.getIndexDay() == 5){
                agendaFri.add(staffItemDto);
            }

            if(staffItemDto.getIndexDay() == 6){
                agendaSat.add(staffItemDto);
            }

            if(staffItemDto.getIndexDay() == 0){
                agendaSun.add(staffItemDto);
            }

        }
        AgendaDto agendaDto = new AgendaDto();

            agendaDto.setMon(agendaMon);
            agendaDto.setTue(agendaTue);
            agendaDto.setWed(agendaWed);
            agendaDto.setThu(agendaThu);
            agendaDto.setFri(agendaFri);
            agendaDto.setSat(agendaSat);
            agendaDto.setSun(agendaSun);

        workplanDto.setAgenda(agendaDto);
        return workplanDto;
    }
}
