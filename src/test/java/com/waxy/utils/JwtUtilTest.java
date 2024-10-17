package com.waxy.utils;

import org.junit.jupiter.api.Test;

import static com.waxy.utils.JwtUtil.generateJwtToken;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {


    @Test
    public void generateJwtTokenTest(){
        String token = generateJwtToken("an");

        System.out.println(token);
        assertEquals(token.length()> 25, true);
    }
}