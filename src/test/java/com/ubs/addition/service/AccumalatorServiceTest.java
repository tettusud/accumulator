package com.ubs.addition.service;

import com.ubs.addition.service.AccumalatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccumalatorServiceTest {

    AccumalatorService calculatorService;

    @BeforeEach
    public void setUp(){
        this.calculatorService= new AccumalatorService();
    }


    @Test
    public void testSimpleAddition(){
        Integer actual = this.calculatorService.execute("2,3");
        Integer expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void testChangeDelimiter(){
        Integer actual = this.calculatorService.execute("//;\\n1;2");
        Integer expected = 3;
        assertEquals(expected, actual);

        actual = this.calculatorService.execute("1;2;3;4");
        expected = 10;
        assertEquals(expected, actual);

    }
}
