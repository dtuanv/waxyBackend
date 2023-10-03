package com.waxy.service.register;

import com.waxy.database.dto.UserDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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


}