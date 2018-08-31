package com.ubs.addition.command;

public interface Command<T,U> {

    /***
     *
     */
      T execute(U u);
}
