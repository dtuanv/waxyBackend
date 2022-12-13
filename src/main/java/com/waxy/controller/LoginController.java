package com.waxy.controller;

import com.waxy.request.LoginRequest;
import com.waxy.response.LoginResponse;
import com.waxy.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
//@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;

    public LoginController(@Autowired LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/api/login")
    @CrossOrigin
    public ResponseEntity<?> doLogin(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.doLogin(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
