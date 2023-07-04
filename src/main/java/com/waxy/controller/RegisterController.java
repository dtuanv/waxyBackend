package com.waxy.controller;


import com.waxy.request.RegisterRequest;
import com.waxy.security.response.RegisterResponse;
import com.waxy.service.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
//@RequestMapping("/api")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(@Autowired RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/api/register")
    @CrossOrigin
    public ResponseEntity<?> doRegister(@RequestBody @Valid RegisterRequest registerRequest) {
        RegisterResponse registerResponse = registerService.doRegister(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }
}
