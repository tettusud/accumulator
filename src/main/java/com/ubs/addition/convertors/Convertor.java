package com.ubs.addition.convertors;

public interface Convertor<T,U> {

    U[] convert(T[] t);
}
