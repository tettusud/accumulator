package com.ubs.addition.command;


import java.util.Arrays;

/**
 * Command model to perform additions
 */
public class AdditionCommand implements Command<Integer> {

    private Integer[] processed;

    public AdditionCommand(Integer[] processed) {
        this.processed = processed;
    }


    @Override
    public Integer execute() {
        if (this.processed.length == 0) {
            return 0;
        } else if (processed.length == 1) {    //just return the number if only one
            return this.processed[0];
        }
        //handle negative numbers
        Integer[] negativeNumbers = Arrays.stream(processed)
                .filter(i -> i<0 )
                 .toArray( size -> new Integer[size]);

        if (negativeNumbers.length > 0) {
            throw new IllegalArgumentException("negatives not allowed -" + Arrays.toString(negativeNumbers));
        }

        return Arrays.stream(processed)
                .mapToInt(i -> i)// convert to integer
                .filter(i -> i <= 1000)  // filter less that 1000
                .sum();  //make a sum
    }


}
