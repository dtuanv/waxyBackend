package com.waxy.service.login;


import com.waxy.request.LoginRequest;
import com.waxy.security.response.LoginResponse;

public interface LoginService {

    LoginResponse doLogin(LoginRequest loginRequest);
}
