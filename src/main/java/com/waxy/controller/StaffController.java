package com.waxy.controller;

import com.waxy.database.entity.StaffItem;
import com.waxy.database.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class StaffController {
    final StaffRepository staffRepository;

    @PostMapping("/saveStaff")
    private void saveStaff(@RequestBody Set<StaffItem> staffSet){
        staffRepository.saveAll(staffSet);
    }
}
