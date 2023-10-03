package com.waxy.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUser {
    private long bAdminId;

    private long businessId;

    private long updateUserId;

    private String role;
    private String password;
}
