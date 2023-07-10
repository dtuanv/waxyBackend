package com.waxy.controller;

import com.waxy.database.entity.Business;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.service.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final UserInfoRepository userInfoRepository;

    @PostMapping("/updateUserInfo")
    private void updateUserInfo(@RequestBody UserInfo userInfo){

        userInfoService.saveUserInfo(userInfo);
    }

    @GetMapping("/userInfo/id/{userInfoId}")
    private UserInfo getUserInfoById(@PathVariable long userInfoId){
        return userInfoRepository.findById(userInfoId).orElseThrow(() -> new IllegalArgumentException(
                String.format("UserInfo can be not found by ID : "+ userInfoId)
        ));
    }

    @GetMapping("/updateVacation/userId/{userId}/restVacation/{restVacation}")
    private void updateVacation(@PathVariable int userId , @PathVariable int restVacation){
        userInfoRepository.updateVacationInUserInfo(restVacation,userId);
    }

    @GetMapping("/getRestVacation/user/{userId}")
    private int getRestVacation(@PathVariable int userId){
      return  userInfoRepository.findRestVacationByUserId(userId);
    }

    @GetMapping("/getUserInfo/business/{businessId}")
    private List<UserInfo> getBusinessByBusinessId(@PathVariable long businessId){
        return userInfoRepository.findUserInfoByBusinessId(businessId);
    }

    @PostMapping("/updateUserRole")
    private void updateUserInfoRole(@RequestBody UserInfo userInfo){
        System.out.println("userInfo.role" + userInfo.getRole());
        if(userInfo.getRole().equals("business_admin")){
            userInfoRepository.updateUserInfoRole("user", userInfo.getId());
        }
        if(userInfo.getRole().equals("user")){
            userInfoRepository.updateUserInfoRole("business_admin", userInfo.getId());
        }
    }
}
