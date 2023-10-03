package com.waxy.security.response;

import com.waxy.database.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private boolean isRegistered;
    private String messsage;

    private UserInfo userInfo;

    public RegisterResponse() {

    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
