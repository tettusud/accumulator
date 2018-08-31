package com.ubs.addition.command;


import com.ubs.addition.processor.InputProcessor;
import com.ubs.addition.processor.impl.DelimiterProcessor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdditionCommandTest {

    Command<Integer> command;


    @Test
    public void testSimpleAddition() {

        Integer actual = getActual("");
        Integer expected = 0;
        assertEquals(expected, actual);

    }

    @Test
    public void testSingleNumber() {

        Integer actual = getActual("2");
        Integer expected = 2;
        assertEquals(expected, actual);

    }

    @Test
    public void testMultipleNumber() {

        Integer actual = getActual("2,3");
        Integer expected = 5;
        assertEquals(expected, actual);
    }


    @Test
    public void testNewLineOrDefaultDelimiter() {
        Integer actual = getActual("1\\n2,3");
        Integer expected = 6;
        assertEquals(expected, actual);
    }


    @Test
    public void testArrayWithChangeDelimiterPattern() {
        Integer actual = getActual("//;\\n1;2");
        assertTestCase(3, actual);
    }


    /***
     * After changing the delimiter pattern pass additional input to check if
     * its still returning the expected input
     */
    @Test
    public void testArrayWithChangeDelimiterPatternSeries() {
        InputProcessor<String,String> inputProcessor =new DelimiterProcessor();
        String numbers= "//;\\n1;2";
        String[] processed=  inputProcessor.process(numbers);
        //return new AdditionCommand(stringToIntegerArray(processed));
        command = new AdditionCommand(stringToIntegerArray(processed));
        Integer actual = command.execute();
        assertTestCase(3, actual);
        // update
        numbers ="1;2;3;4";
        processed=  inputProcessor.process(numbers);
        actual =  new AdditionCommand(stringToIntegerArray(processed)).execute();
        assertTestCase(10, actual);
    }

    /**
     * Test case for the scenerio
     * 7.Delimiters can be of any length, for example: “//***\n1***2***3” should return 6.
     */
    @Test
    public void testArrayWithCustomDelimiterWithAnyLength() {
        Integer actual = getActual("//**\\n1**2");
        assertTestCase(3, actual);
    }

    @Test
    public void testArrayWithCustomDelimiterWithMetaCharacters() {
        Integer actual = getActual("//###\\n1###2###3");
        assertTestCase(6, actual);
    }

    @Test
    public void testArrayWithCustomDelimiterWithMultiDelimiters() {
        Integer actual = getActual("//*|%\\n1*2%3");
        assertTestCase(6, actual);
    }

    @Test
    public void testSpecialCaseWithPipeAsDelimiter() {
        Integer actual = getActual("//*||\\n1*2|3|4");
        assertTestCase(10, actual);
    }

    /**
     * 6.Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2.
     */
    @Test
    public void testNumberGreaterThan1000() {
        Integer actual = getActual("2,1001,3");
        assertTestCase(5, actual);
    }

    /**
     * 6.Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2.
     */
    @Test
    public void testForNegativeNumbers() {
        String negativeArray ="[-6, -4, -5]";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->   getActual("2,-6,-4,-5,3"));
        assertEquals("negatives not allowed -"+negativeArray, exception.getMessage());
    }


    /**
     * 9. Make sure you can also handle multiple delimiters with length longer than one character.
     */
    @Test
    public void testArrayWithCustomDelimiterWithMultiDelimitersWithAnyLength() {
        Integer actual = getActual("//*|%%\\n1*2%%3*4");
        assertTestCase(10, actual);
    }



    private AdditionCommand getCommand(String numbers) {
        InputProcessor<String,String> inputProcessor =new DelimiterProcessor();
        String[] processed=  inputProcessor.process(numbers);

        return new AdditionCommand(stringToIntegerArray(processed));
    }

    /**
     * helper method
     *
     * @param numbers
     * @return
     */
    private Integer getActual(String numbers) {
        return getCommand(numbers).execute();

    }

    private void assertTestCase(Integer expected, Integer actual) {
        assertEquals(expected, actual);
    }

    private Integer[] stringToIntegerArray(String[] input){
        return Arrays.stream(input)
                .map(i -> Integer.parseInt(i))// convert to integer
                .toArray(size->new Integer[size]);
    }
}
