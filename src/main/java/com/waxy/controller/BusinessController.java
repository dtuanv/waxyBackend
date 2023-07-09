package com.waxy.controller;

import com.waxy.database.entity.Business;
import com.waxy.database.repository.BusinessRepository;
import com.waxy.database.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessRepository businessRepository;
    private final UserInfoRepository userInfoRepository;

    @GetMapping("/allBusiness")
    private List<Business> getAllBusiness(){
        List<Business> businessList = businessRepository.findAll();

        for (Business business: businessList  ) {
            business.setNumberUsers(userInfoRepository.countUserOfBusiness(business.getId()));
        }
        return businessList;
    }


    @PostMapping("/saveBusiness")
    private void saveBusiness(@RequestBody Business business){
        businessRepository.save(business);
    }
}
