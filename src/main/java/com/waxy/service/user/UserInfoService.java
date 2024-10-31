package com.waxy.service.user;

import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.database.repository.WishMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    WishMessageRepository wishMessageRepository;

    public boolean hasUserNotSentMessage(long fromUserId, long birthUserId, String today){
        int size = wishMessageRepository.checkWishMessageFromUser(fromUserId,birthUserId,today).stream().collect(Collectors.toSet()).size() ;
        if(size == 0){
            return true;
        }else {
            return false ;
        }
    }

    public void saveUserInfo(UserInfo userInfo){
        UserInfo userInfoUpdate;
        if(userInfo.getId() > 0){
         userInfoUpdate =   userInfoRepository.findById(userInfo.getId()).orElseThrow(()
                    -> new IllegalArgumentException(String.format("UserInfo can be not found by ID: "+userInfo.getId())));
        }else {
            userInfoUpdate = new UserInfo();
        }

        userInfoUpdate.setUserId(userInfo.getUserId());

        userInfoUpdate.setDepartment(userInfo.getDepartment());

        userInfoUpdate.setAvatar(userInfo.getAvatar());

        userInfoUpdate.setBusinessId(userInfo.getBusinessId());

        userInfoUpdate.setName(userInfo.getName());

        userInfoUpdate.setRole(userInfo.getRole());

        userInfoUpdate.setBirthday(userInfo.getBirthday());

        userInfoUpdate.setPosition(userInfo.getPosition());

        userInfoUpdate.setLanguage(userInfo.getLanguage());

        userInfoUpdate.setRestVacation(userInfo.getRestVacation());


        userInfoRepository.save(userInfoUpdate);
    }

}
