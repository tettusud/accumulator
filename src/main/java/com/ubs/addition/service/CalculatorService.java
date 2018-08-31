package com.ubs.addition.service;

public interface CalculatorService<T,U> {

    /**
     *
     * @param t
     * @return
     */
     U execute(T t);
}
