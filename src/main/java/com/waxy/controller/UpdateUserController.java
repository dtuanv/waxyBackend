package com.waxy.controller;

import com.waxy.security.response.UpdateUserResponse;
import com.waxy.service.updateUser.UpdateUserService;
import com.waxy.utils.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UpdateUserController {

    final UpdateUserService updateUserService;


    @PostMapping("/updatePasswordUser")
    private ResponseEntity<?> adminUpdatePasswordUser(@RequestBody UpdateUser updateUser){
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        if(updateUser.getRole().contains("admin")){
            updateUserResponse = updateUserService.adminDoUpdateUser(updateUser);
//            System.out.println("Change PWD");
        }else{
            updateUserResponse.setUpdated(false);
            updateUserResponse.setMessage("Error, please try again login");
        }
        return ResponseEntity.ok(updateUserResponse);
    }

    @GetMapping("/updatePassword/{confirmPassword}/userInfo/id/{userInfoId}/userId/{userId}")
    private ResponseEntity<?> userUpdatePassword(@PathVariable String confirmPassword,@PathVariable  long userInfoId,@PathVariable  long userId){
        UpdateUserResponse updateUserResponse = updateUserService.userDoUpdateUser(confirmPassword, userInfoId, userId);
        return ResponseEntity.ok(updateUserResponse);
    }
}
