package com.ubs.addition;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceTest {

    CalculatorService calculatorService;

    @BeforeEach
    public void setUp(){
        this.calculatorService= new CalculatorService();
    }


    @Test
    public void testSimpleAddition(){
        Integer actual = this.calculatorService.accumalate("2,3");
        Integer expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void testChangeDelimiter(){
        Integer actual = this.calculatorService.accumalate("//;\\n1;2");
        Integer expected = 3;
        assertEquals(expected, actual);

        actual = this.calculatorService.accumalate("1;2;3;4");
        expected = 10;
        assertEquals(expected, actual);

    }
}
