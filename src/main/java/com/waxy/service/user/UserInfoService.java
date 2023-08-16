package com.waxy.service.user;

import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

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


        userInfoRepository.save(userInfoUpdate);
    }

}
