package com.ubs.addition.processor;

public interface InputProcessor<T,U> {


  U[] process(T numbers);

}
