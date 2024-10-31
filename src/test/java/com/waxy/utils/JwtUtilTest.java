package com.waxy.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.waxy.utils.JwtUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    @Mock // Mock the EnvUtil
    private EnvUtil envUtil;

    @InjectMocks // Inject mocks into the JwtUtil instance
    private JwtUtil jwtUtil;
    static  String token;
    static String username = "user@waxy";
    @BeforeEach
    public  void setUp(){

        // Setup the environment variable mock
        jwtUtil.setJwtSecretKey("MOCKKEY");
        // Generate a token for testing
        token = jwtUtil.generateJwtToken(username);
    }

    @Test
    public void testGenerateJwtToken(){
        String token = jwtUtil.generateJwtToken("an");

        assertEquals(token.length()> 25, true);
    }

    @Test
    public void testGetUsernameFromJwtToken() {
        String usernameRes = jwtUtil.getUsernameFromJwtToken(token);

        assertEquals(usernameRes,username);
    }

    @Test
    public void  testValidateJwtToken(){
        boolean validTokenResponse = jwtUtil.validateJwtToken(token);
        boolean invalidTokenResponse = validateJwtToken(token+ "in");

        assertEquals(validTokenResponse, true);
        assertEquals(invalidTokenResponse, false);

    }
}