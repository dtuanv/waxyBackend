package com.waxy.service.register;

import com.waxy.request.RegisterRequest;
import com.waxy.security.response.RegisterResponse;


public interface RegisterService {
    RegisterResponse doRegister(RegisterRequest registerRequest);

}
