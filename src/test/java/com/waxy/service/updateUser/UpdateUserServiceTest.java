package com.waxy.service.updateUser;

import com.waxy.dto.UserDTO;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.RegisterRepository;
import com.waxy.database.repository.UserInfoRepository;

import com.waxy.security.response.UpdateUserResponse;
import com.waxy.utils.UpdateUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {

    @InjectMocks
    private UpdateUserImpl updateUserService;
    @Mock
    private RegisterRepository registerRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    UserInfoRepository userInfoRepository;

    @Test
    public void whenAdminUpdateUser_ThenReturnSuccessMessage(){
        UpdateUser updateUser = UpdateUser.builder().updateUserId(2).bAdminId(1).role("admin")
                .password("user").businessId(1)
                .build();

//        UserInfo userInfo = UserInfo.builder().businessId(1L).role("business_admin").userId(2).build();

        UserInfo userInfoAdmin = new UserInfo();
        userInfoAdmin.setId(1);
        userInfoAdmin.setUserId(1);
        userInfoAdmin.setBusinessId(1l);
        userInfoAdmin.setRole("business_admin");

        UserInfo userInfoUpdate = new UserInfo();
        userInfoUpdate.setId(3);
        userInfoUpdate.setUserId(2);
        userInfoUpdate.setBusinessId(1l);
        userInfoUpdate.setRole("user");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(2);
        userDTO.setUsername("user@zatakub");

        when(userInfoRepository.findById(updateUser.getBAdminId())).thenReturn(Optional.ofNullable(userInfoAdmin));

        when(registerRepository.findById(updateUser.getUpdateUserId())).thenReturn(Optional.ofNullable(userDTO));

        when(userInfoRepository.findById(updateUser.getUpdateUserInfoId())).thenReturn(Optional.of(userInfoUpdate));

        userInfoUpdate.setFirstLogin(true);

        when(userInfoRepository.save(any())).thenReturn(userInfoUpdate);
        UpdateUserResponse updateUserResponse = updateUserService.adminDoUpdateUser(updateUser);





        Assertions.assertThat(updateUserResponse.getUserInfo().getId()).isEqualTo(userInfoUpdate.getId());


        org.assertj.core.api.Assertions.assertThat(updateUserResponse.isUpdated()).isTrue();

        org.assertj.core.api.Assertions.assertThat(updateUserResponse.getUserInfo().isFirstLogin()).isTrue();

        org.assertj.core.api.Assertions.assertThat(updateUserResponse.getMessage()).isEqualTo("New password is: user");
    }

    @Test
    public void whenUserUpdatePassword_ThenReturnSuccessMessage(){
                UserDTO userDTO = new UserDTO();
                userDTO.setId(1);
                userDTO.setUsername("user");
                userDTO.setPassword("u");
                UserInfo userInfo = new UserInfo();
                userInfo.setFirstLogin(true);
                userInfo.setUserId(1);
                userInfo.setId(2);
                userInfo.setBusinessId(1L);
                userInfo.setRole("user");

                when(registerRepository.findById(1L)).thenReturn(Optional.of(userDTO));
                when(userInfoRepository.findById(2L)).thenReturn(Optional.of(userInfo));
                //Act
        UpdateUserResponse updateUserResponse = updateUserService.userDoUpdateUser("pwd",2,1);

        Assertions.assertThat(updateUserResponse.getUserInfo()).isNotNull();
        Assertions.assertThat(updateUserResponse.getUserInfo().isFirstLogin()).isFalse();

    }
}