package com.ubs.addition.calculator;

import com.ubs.addition.calculator.impl.CalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    Calculator calculator;
    @BeforeEach
    public void setUp(){
        this.calculator = new CalculatorImpl();
    }

    @Test
    public void testAddition(){
        assertEquals( 1, calculator.add(0,1));
    }
}
