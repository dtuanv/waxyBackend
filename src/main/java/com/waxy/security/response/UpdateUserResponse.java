package com.waxy.security.response;

import com.waxy.database.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserResponse {

    private String message;

    private boolean isUpdated;

    private UserInfo userInfo;
}
