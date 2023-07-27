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
        UserInfo userInfoUpdate;
        if(userInfo.getId() > 0){
            userInfoUpdate = userInfoRepository.findById(userInfo.getId()).orElseThrow(() ->
                    new IllegalArgumentException(String.format("UserInfo can not be found By ID: "+userInfo.getId() )));
        }else {
            userInfoUpdate = new UserInfo();
        }

        userInfoUpdate.setUserId(userInfo.getUserId());
        userInfoUpdate.setDepartment(userInfo.getDepartment());
        userInfoUpdate.setAvatar(userInfo.getAvatar());

        userInfoUpdate.setBusinessId(userInfo.getBusinessId());

        userInfoUpdate.setName(userInfo.getName());

        userInfoUpdate.setRole(userInfo.getRole());

        userInfoUpdate.setBusinessId(userInfo.getBusinessId());


        userInfoService.saveUserInfo(userInfo);
    }

    @GetMapping("/business/{businessId}/checkTodayIsBirthdayOfWhom/{today}/fromUser/{fromUserId}")
    private Set<UserInfo> getUserInfoHasBirthday(@PathVariable long businessId,@PathVariable String today, @PathVariable long fromUserId){
        Set<UserInfo> userInfoSet = userInfoRepository.findUserInfoTodayHasBirthday(businessId,today);

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

    @DeleteMapping("/deleteUserInfo/{userInfoId}/user/{userId}")
    private void deleteUserInfo(@PathVariable long userInfoId, @PathVariable long userId){
        userInfoRepository.deleteById(userInfoId);
        userRepository.deleteById(userId);
    }


}
