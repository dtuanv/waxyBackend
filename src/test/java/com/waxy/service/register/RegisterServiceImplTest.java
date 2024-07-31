package com.waxy.service.register;

import com.waxy.dto.UserDTO;
import com.waxy.dto.UserRoleDTO;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.RegisterRepository;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.request.RegisterRequest;
import com.waxy.security.response.RegisterResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(MockitoExtension.class)
class RegisterServiceImplTest {

    @InjectMocks
    RegisterServiceImpl registerService;

    @Mock
    private  RegisterRepository registerRepository;

    @Mock
    private  UserInfoRepository userInfoRepository;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Test
    public void doRegisterTest(){
        //arrange
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user");
        registerRequest.setPassword("user");
        registerRequest.setRole("business_admin");

        //Act
        RegisterResponse registerResponse = registerService.doRegister(registerRequest);
       // Assert


        Assertions.assertThat(registerResponse).isNotNull();
        Assertions.assertThat(registerResponse.getMesssage()).isEqualTo("User was saved");


    }
    @Test
    public void whenDoRegisterThenReturnUserInfo(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("user");
        registerRequest.setPassword("user");
        registerRequest.setRole("business_admin");
        UserDTO newUser = mapNewRegisterToNewUserOrUpdateUser(registerRequest);

        when(registerRepository.save(any())).thenReturn(newUser);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(newUser.getId());
        userInfo.setFirstLogin(true);

        when(userInfoRepository.save(any())).thenReturn(userInfo);

        //Act
        RegisterResponse registerResponse = registerService.doRegister(registerRequest);

        Assertions.assertThat(registerResponse.getUserInfo()).isNotNull();
        Assertions.assertThat(registerResponse.getUserInfo().isFirstLogin()).isTrue();
    }



    private UserDTO mapNewRegisterToNewUserOrUpdateUser(RegisterRequest registerRequest) {
        UserDTO userDto = new UserDTO();

        userDto.setUsername(registerRequest.getUsername());
        userDto.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        UserRoleDTO basicRole = new UserRoleDTO();
        basicRole.setRole("ROL_BASIC");
        basicRole.setUserDTO(userDto);

        userDto.getUserRoles().add(basicRole);
        return userDto;
    }


}