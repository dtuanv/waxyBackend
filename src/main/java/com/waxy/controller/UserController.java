package com.waxy.controller;

import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.service.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

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
}
