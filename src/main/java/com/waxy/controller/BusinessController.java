package com.waxy.controller;

import com.waxy.database.entity.Business;
import com.waxy.database.repository.BusinessRepository;
import com.waxy.database.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Business businessUpdate;
        if(business.getId() > 0){
            businessUpdate = businessRepository.findById(business.getId()).orElseThrow( () ->
                    new IllegalArgumentException(String.format("Cannot find business by ID: "+business.getId() )));
        }else {
            businessUpdate = new Business();
        }
        businessUpdate.setName(business.getName());

        businessUpdate.setOwner(business.getOwner());

        businessUpdate.setNumberUsers(business.getNumberUsers());

        businessUpdate.setBusinessArea(business.getBusinessArea());

        businessUpdate.setAddress(business.getAddress());

        businessUpdate.setStatus(business.getStatus());

        businessRepository.save(businessUpdate);
    }

    @GetMapping("/getBusiness/businessId/{businessId}")
    private Business getBusiness(@PathVariable long businessId){
        return businessRepository.findById(businessId).orElseThrow( () ->
                new IllegalArgumentException(String.format("Cannot find business by ID: "+businessId)));
    }
}
