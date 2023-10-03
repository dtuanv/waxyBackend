package com.waxy.controller;

import com.waxy.security.response.UpdateUserResponse;
import com.waxy.service.updateUser.UpdateUserService;
import com.waxy.utils.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateUserController {

    final UpdateUserService updateUserService;

    @PostMapping("/updatePasswordUser")
    private ResponseEntity<?> updatePasswordUser(@RequestBody UpdateUser updateUser){
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        if(updateUser.getRole().contains("admin")){
            updateUserResponse = updateUserService.doUpdateUser(updateUser);
            System.out.println("Change PWD");
        }else{
            updateUserResponse.setUpdated(false);
            updateUserResponse.setMessage("Error, please try again login");
        }
        return ResponseEntity.ok(updateUserResponse);
    }
}
