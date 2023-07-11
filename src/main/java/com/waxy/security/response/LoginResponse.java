package com.waxy.security.response;

import com.waxy.database.dto.UserInfoDto;
import com.waxy.database.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private String jwtToken;

//    private UserInfo userInfo;

    private UserInfoDto userInfo;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

//    public UserInfo getUserInfo(){
//        return userInfo;
//    }
//
//    public void setUserInfo(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
}
