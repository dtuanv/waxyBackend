package com.waxy.controller;

import com.waxy.database.entity.Business;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.entity.WishMessage;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.database.repository.UserRepository;
import com.waxy.database.repository.WishMessageRepository;
import com.waxy.service.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final UserInfoRepository userInfoRepository;

    private final WishMessageRepository wishMessageRepository;

    private final UserRepository userRepository;

    @PostMapping("/updateUserInfo")
    private void updateUserInfo(@RequestBody UserInfo userInfo){
        userInfoService.saveUserInfo(userInfo);
    }

    @GetMapping("/business/{businessId}/checkTodayIsBirthdayOfWhom/{today}/fromUser/{fromUserId}")
    private Set<UserInfo> getUserInfoHasBirthday(@PathVariable long businessId,@PathVariable String today, @PathVariable long fromUserId){
        String[] todayArr = today.split("\\.");
        int day = Integer.parseInt(todayArr[0]) ;
        int month = Integer.parseInt(todayArr[1]) ;
        Set<UserInfo> userInfoSet = userInfoRepository.findUserInfoTodayHasBirthday(businessId,month,day);
        userInfoSet = userInfoSet.stream().filter(userInfo -> checkUserHasSentMessage(fromUserId,userInfo.getUserId(),today)).collect(Collectors.toSet());
            return userInfoSet;
    }

    private boolean checkUserHasSentMessage(long fromUserId, long birthUserId, String today){
        int size = wishMessageRepository.checkWishMessageFromUser(fromUserId,birthUserId,today).stream().collect(Collectors.toSet()).size() ;
        if(size == 0){
            return true;
        }else {
            return false ;
        }
    }
    @GetMapping("/userInfo/id/{userInfoId}")
    private UserInfo getUserInfoById(@PathVariable long userInfoId){
        return userInfoRepository.findById(userInfoId).orElseThrow(() -> new IllegalArgumentException(
                String.format("UserInfo can be not found by ID : "+ userInfoId)
        ));
    }
    @GetMapping("/userInfo/id/{userInfoId}/userId/{userId}")
    private UserInfo getUserInfoByIdCheck(@PathVariable long userInfoId){
        return userInfoRepository.findById(userInfoId).orElseThrow(() -> new IllegalArgumentException(
                String.format("UserInfo can be not found by ID : "+ userInfoId)
        ));
    }

    @GetMapping("/updateVacation/userInfoId/{userInfoId}/totalVacation/{totalVacation}")
    private void updateVacationWhenRequest(@PathVariable long userInfoId , @PathVariable int totalVacation){

        UserInfo userInfo = userInfoRepository.findById(userInfoId).orElseThrow(() -> new IllegalArgumentException(
                String.format("Can not found UserInfo by ID: "+ userInfoId)
        ));
        userInfo.setRestVacation(userInfo.getRestVacation() - totalVacation);
        userInfoRepository.save(userInfo);
    }
    @GetMapping("/updateVacation/userInfoId/{userInfoId}/duration/{duration}")
    private void updateVacationWhenReject(@PathVariable long userInfoId , @PathVariable int duration){
        UserInfo userInfo = userInfoRepository.findById(userInfoId).orElseThrow(() -> new IllegalArgumentException(
                String.format("Can not found UserInfo by ID: "+ userInfoId)
        ));
        userInfo.setRestVacation(userInfo.getRestVacation() + duration);
        userInfoRepository.save(userInfo);
    }

    @GetMapping("/getRestVacation/userInfo/{userInfoId}")
    private int getRestVacation(@PathVariable int userInfoId){
      return  userInfoRepository.findRestVacationByUserInfoId(userInfoId);
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

    @DeleteMapping("/deleteUserInfo/{userInfoId}/user/{userId}")
    private void deleteUserInfo(@PathVariable long userInfoId, @PathVariable long userId){
        userInfoRepository.deleteById(userInfoId);
        userRepository.deleteById(userId);
    }


}
