package com.waxy.service.updateUser;

import com.waxy.database.dto.UserDTO;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.entity.Vacation;
import com.waxy.database.repository.RegisterRepository;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.database.repository.VacationRepository;

import com.waxy.security.response.UpdateUserResponse;
import com.waxy.utils.UpdateUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static jdk.dynalink.linker.support.Guards.isNotNull;
import static org.junit.jupiter.api.Assertions.*;
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
    public void whenUpdateUser_ThenReturnSuccessMessage(){
        UpdateUser updateUser = UpdateUser.builder().updateUserId(2).bAdminId(1).role("business_admin")
                .password("user").businessId(1)
                .build();

//        UserInfo userInfo = UserInfo.builder().businessId(1L).role("business_admin").userId(2).build();

        UserInfo userInfoAdmin = new UserInfo();
        userInfoAdmin.setId(1);
        userInfoAdmin.setUserId(1);
        userInfoAdmin.setBusinessId(1l);
        userInfoAdmin.setRole("business_admin");

        UserInfo userInfoUpdate = new UserInfo();
        userInfoUpdate.setId(2);
        userInfoUpdate.setUserId(2);
        userInfoUpdate.setBusinessId(1l);
        userInfoUpdate.setRole("user");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(2);
        userDTO.setUsername("user@zatakub");

        when(userInfoRepository.findById(updateUser.getBAdminId())).thenReturn(Optional.ofNullable(userInfoAdmin));

        when(registerRepository.findById(updateUser.getUpdateUserId())).thenReturn(Optional.ofNullable(userDTO));

        when(userInfoRepository.findById(updateUser.getUpdateUserId())).thenReturn(Optional.of(userInfoUpdate));
        userInfoUpdate.setFirstLogin(true);

        when(userInfoRepository.save(any())).thenReturn(userInfoUpdate);

       UpdateUserResponse updateUserResponse = updateUserService.doUpdateUser(updateUser);

        org.assertj.core.api.Assertions.assertThat(updateUserResponse.isUpdated()).isTrue();

        org.assertj.core.api.Assertions.assertThat(updateUserResponse.getUserInfo().isFirstLogin()).isTrue();

        org.assertj.core.api.Assertions.assertThat(updateUserResponse.getMessage()).isEqualTo("New password is: user");
    }


}