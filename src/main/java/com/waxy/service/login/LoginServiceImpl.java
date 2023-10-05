package com.waxy.service.login;

import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.request.LoginRequest;
import com.waxy.security.response.LoginResponse;
import com.waxy.security.SecureUser;
import com.waxy.service.mapper.UserInfoMapper;
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
    @Autowired
    private  UserInfoMapper userInfoMapper;
    private UserInfo userInfo;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public LoginServiceImpl(@Autowired AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public LoginResponse doLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername().toLowerCase(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecureUser userDetails = (SecureUser) authentication.getPrincipal();
        String jwtToken = JwtUtil.generateJwtToken(userDetails.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwtToken(jwtToken);
        UserInfo  userInfo = userInfoRepository.findByUsername(userDetails.getUsername());


        if(userInfo != null){
            loginResponse.setUserInfo(userInfoMapper.mapToDto(userInfo));
        }

        return loginResponse;
    }


//    public UserInfo getUserInfo() {
//        return this.userInfo;
//    }
//
//    public void setUserInfo(UserInfo userInfo){
//        this.userInfo = userInfo;
//    }
}
