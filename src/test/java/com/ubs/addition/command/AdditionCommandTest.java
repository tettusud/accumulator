package com.ubs.addition.command;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdditionCommandTest {

    Command<Integer, String> command;


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
        command = getCommand();
        Integer actual = command.execute("//;\\n1;2");
        assertTestCase(3, actual);
        actual = command.execute("1;2;3;4");
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
     * 9. Make sure you can also handle multiple delimiters with length longer than one character.
     */
    @Test
    public void testArrayWithCustomDelimiterWithMultiDelimitersWithAnyLength() {
        Integer actual = getActual("//*|%%\\n1*2%%3*4");
        assertTestCase(10, actual);
    }

    private AdditionCommand getCommand() {
        return new AdditionCommand();
    }

    /**
     * helper method
     *
     * @param numbers
     * @return
     */
    private Integer getActual(String numbers) {
        return getCommand().execute(numbers);

    }

    private void assertTestCase(Integer expected, Integer actual) {
        assertEquals(expected, actual);
    }


}
