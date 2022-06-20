package com.railway.railwayservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RailwayServiceApplicationTests {

    Calculator calculator = new Calculator();

    @Test
    void contextLoads() {
        int numberOne = 20;
        int numberTwo = 30;

        int result = calculator.addTwoNumbers(numberOne, numberTwo);

        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }



    class Calculator {
        int addTwoNumbers(int a, int b){
            return a+b;
        }
    }
}
