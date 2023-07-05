package com.waxy.controller;

import com.waxy.database.dto.WorkplanDto;
import com.waxy.database.entity.Workplan;
import com.waxy.database.repository.StaffRepository;
import com.waxy.database.repository.WorkplanRepository;
import com.waxy.service.mapper.WorkplanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WorkplanController {
    final WorkplanRepository workplanRepository;

    final StaffRepository staffRepository;

    final WorkplanMapper workplanMapper;

    @PostMapping("/saveWorkplan")
    private void saveWorkplan(@RequestBody Workplan workplan){
//        Set<StaffItem> staffItemSet = workplan.getStaffItemSet();
//            for(StaffItem staffItem : staffItemSet){
//                System.out.println("(agenda.getStaffItem() oben, "+staffItem.getIndexDay());
//                if(staffItem.getTitle() != null){
//                    System.out.println("agenda.getStaffItem() ");
//                    staffRepository.save( staffItem)   ;
//
//                }
//
//            }
        workplanRepository.save(workplan);
    }

    @GetMapping("/allWorkplan/{businessId}")
    private Set<WorkplanDto> getAllWorkplanByBusinessId(@PathVariable int businessId){
        return workplanRepository.findWorkplanByBusinessId(businessId).stream().map(workplanMapper :: mapToDto).collect(Collectors.toSet());
    }



}
