package com.waxy.service.login;

import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.request.LoginRequest;
import com.waxy.response.LoginResponse;
import com.waxy.security.SecureUser;
import com.waxy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;

    private UserInfo userInfo;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public LoginServiceImpl(@Autowired AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public LoginResponse doLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecureUser userDetails = (SecureUser) authentication.getPrincipal();



        String jwtToken = JwtUtil.generateJwtToken(userDetails.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwtToken(jwtToken);

        UserInfo  user = userInfoRepository.findByUsername(userDetails.getUsername());

        if(user != null){
            loginResponse.setUserInfo(user);
        }

        return loginResponse;
    }


    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }
}
