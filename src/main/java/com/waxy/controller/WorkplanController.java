package com.waxy.controller;

import com.waxy.dto.WorkplanDto;
import com.waxy.database.entity.Workplan;
import com.waxy.database.repository.StaffRepository;
import com.waxy.database.repository.WorkplanRepository;
import com.waxy.service.mapper.WorkplanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        if(workplan.getId() > 0){
            workplanRepository.deleteById(workplan.getId());
            System.out.println("wp deleted");
        }
        System.out.println("id "+ workplan.getId());
        workplanRepository.save(workplan);
    }

    @GetMapping("/allWorkplan/{businessId}")
    private List<WorkplanDto> getAllWorkplanByBusinessId(@PathVariable int businessId){
        return workplanRepository.findWorkplanByBusinessId(businessId).stream().map(workplanMapper :: mapToDto).collect(Collectors.toList());
    }



}
