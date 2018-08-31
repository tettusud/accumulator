package com.ubs.addition.calculator;

import com.ubs.addition.calculator.impl.CalculatorImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {


    Calculator calculator;


    @BeforeAll
    public void setUp(){
        this.calculator = new CalculatorImpl();
    }

    @Test
    public void testAddition(){
        assertEquals( 1, calculator.add(0,1));
    }
}
