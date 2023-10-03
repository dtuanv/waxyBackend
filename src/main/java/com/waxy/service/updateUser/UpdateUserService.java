package com.waxy.service.updateUser;

import com.waxy.security.response.UpdateUserResponse;
import com.waxy.utils.UpdateUser;

public interface UpdateUserService {
    UpdateUserResponse doUpdateUser(UpdateUser updateUser);
}
