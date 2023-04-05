package com.waxy.response;

import com.waxy.database.entity.UserInfo;

public class LoginResponse {
    private String jwtToken;

    private UserInfo userInfo;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserInfo getUserInfo(){
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
