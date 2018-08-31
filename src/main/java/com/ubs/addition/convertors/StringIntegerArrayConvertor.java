package com.ubs.addition.convertors;

import java.util.Arrays;

/***
 *  This class converts array of String to array of Integer
 */
public class StringIntegerArrayConvertor implements  Convertor<String,Integer>{
    @Override
    public Integer[] convert(String[] t) {
        return Arrays.stream(t)
                .map(i -> Integer.parseInt(i))// convert to integer
                .toArray(size->new Integer[size]);
    }
}
