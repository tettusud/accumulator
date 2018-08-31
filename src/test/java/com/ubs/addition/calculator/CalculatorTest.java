package com.ubs.addition.calculator;

import com.ubs.addition.calculator.impl.CalculatorImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {


    Calculator calculator;


    @Before
    public void setUp(){
        this.calculator = new CalculatorImpl();
    }

    @Test
    public void testAddition(){
        assertEquals( 1, calculator.add(0,1));
    }
}
