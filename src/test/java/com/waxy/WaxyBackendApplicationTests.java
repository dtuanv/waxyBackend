package com.waxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class WaxyBackendApplicationTests {
    Calculator underTest = new Calculator();
    @Test
    void contextLoads() {
        //given
        int numberOne = 20;

        int numberTwo = 30;

        // When
       int result =  underTest.add(numberOne, numberTwo);

       // Then
        assertEquals(50, result);

    }

    class Calculator {
        int add(int a, int b){
            return a +b;
        }
    }
}
