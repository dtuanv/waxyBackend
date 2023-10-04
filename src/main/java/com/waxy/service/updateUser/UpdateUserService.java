package com.waxy.service.updateUser;

import com.waxy.security.response.UpdateUserResponse;
import com.waxy.utils.UpdateUser;

public interface UpdateUserService {
    UpdateUserResponse adminDoUpdateUser(UpdateUser updateUser);

    UpdateUserResponse userDoUpdateUser(String password, long userInfoId, long userId);
}
