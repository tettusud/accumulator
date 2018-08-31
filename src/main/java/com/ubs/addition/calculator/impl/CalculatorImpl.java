package com.ubs.addition.calculator.impl;

import com.ubs.addition.calculator.Calculator;

/**
 *  Calculator operations
 *    1.add
 *    2.future operations
 */
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int number1, int number2) {
        return number1+number2;
    }
}
