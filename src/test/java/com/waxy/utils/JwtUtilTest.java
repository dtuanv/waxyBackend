package com.waxy.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.waxy.utils.JwtUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    static  String token;
    static String username = "user@waxy";
    @BeforeAll
    public static void setUp(){
         token =  generateJwtToken(username);
    }

    @Test
    public void testGenerateJwtToken(){
        String token = generateJwtToken("an");

        System.out.println(token);
        assertEquals(token.length()> 25, true);
    }

    @Test
    public void testGetUsernameFromJwtToken() {
        String usernameRes = getUsernameFromJwtToken(token);

        assertEquals(usernameRes,username);
    }

    @Test
    public void  testValidateJwtToken(){
        boolean validTokenResponse = validateJwtToken(token);
        boolean invalidTokenResponse = validateJwtToken(token+ "in");

        assertEquals(validTokenResponse, true);
        assertEquals(invalidTokenResponse, false);

    }
}