package com.ubs.addition.command;


import com.ubs.addition.processor.InputProcessor;
import com.ubs.addition.processor.impl.DelimiterProcessor;

import java.util.Arrays;

/**
 * Command model to perform additions
 */
public class AdditionCommand implements Command<Integer,String> {


    private InputProcessor<String, String> inputProcessor;

    public AdditionCommand() {
        this.inputProcessor = new DelimiterProcessor();
    }

    @Override
    public Integer execute(String numbers) {
        String[] processed = this.inputProcessor.process(numbers);
        if (processed.length == 0) {
            return 0;
        } else if (processed.length == 1) {    //just return the number if only one
            return Integer.parseInt(processed[0]);
        }
        return Arrays.stream(processed)
                .mapToInt(i -> Integer.parseInt(i))// convert to integer
                .filter(i -> i <= 1000)  // filter less that 1000
                .sum();  //make a sum
    }


}
