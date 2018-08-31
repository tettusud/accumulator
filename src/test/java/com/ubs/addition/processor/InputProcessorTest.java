package com.ubs.addition.processor;

import com.ubs.addition.processor.impl.DelimiterProcessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/***
 *   Test case for InputProcess
 */
public class InputProcessorTest {

    InputProcessor<String,String> inputProcessor;
    @BeforeAll
    public void setUp(){
        this.inputProcessor = new DelimiterProcessor();
    }

    @Test
    public void testArrayDefaultDelimiterWithEmptyInput(){
        String[] actual= inputProcessor.process("");
        String[] expectedResult = new String[]{};
        assertTestCases(expectedResult,actual);
    }

    @Test
    public void testArrayDefaultDelimiterWithSingleNumber(){
        String[] actual= inputProcessor.process("1");
        String[] expectedResult = new String[]{"1"};
        assertTestCases(expectedResult,actual);
    }

    @Test
    public void testArrayDefaultDelimiterWithTwoNumbers(){
        String[] actual= inputProcessor.process("1,2");
        String[] expectedResult = new String[]{"1","2"};
        assertTestCases(expectedResult,actual);
    }


    @Test
    public void testArrayDefaultDelimiterWithMultipleNumbers(){
        String[] actual= inputProcessor.process("1,2,3,4");
        String[] expectedResult = new String[]{"1","2","3","4"};
        assertTestCases(expectedResult,actual);
    }

    @Test
    public void testNewLineOrDefaultDelimiter(){
        String[] actual= inputProcessor.process("1\\n2,3");
        String[] expectedResult = new String[]{"1","2","3"};
        assertTestCases(expectedResult,actual);
    }

    @Test
    public void testArrayWithChangeDelimiterPattern(){
        String[] actual= inputProcessor.process("//;\\n1;2");
        String[] expectedResult = new String[]{"1","2"};
        assertTestCases(expectedResult,actual);
    }


    /***
     * After changing the delimiter pattern pass additional input to check if
     * its still returning the expected input
     */
    @Test
    public void testArrayWithChangeDelimiterPatternSeries(){
        String[] actual= inputProcessor.process("//;\\n1;2");
        String[] expectedResult = new String[]{"1","2"};
        assertTestCases(expectedResult,actual);

        actual= inputProcessor.process("1;2;3;4");
        expectedResult = new String[]{"1","2","3","4"};
        assertTestCases(expectedResult,actual);
    }

    /**
     * Test case for the scenerio
     * 7.Delimiters can be of any length, for example: “//***\n1***2***3” should return 6.
     */
    @Test
    public void testArrayWithCustomDelimiterWithAnyLength(){
        String[] actual= inputProcessor.process("//**\\n1**2");
        String[] expectedResult = new String[]{"1","2"};
        assertTestCases(expectedResult,actual);
    }

    @Test
    public void testArrayWithCustomDelimiterWithMetaCharacters(){
        String[] actual= inputProcessor.process("//###\\n1###2###3");
        String[] expectedResult = new String[]{"1","2","3"};
        assertTestCases(expectedResult,actual);
    }

    @Test
    public void testArrayWithCustomDelimiterWithMultiDelimiters(){
        String[] actual= inputProcessor.process("//*|%\\n1*2%3");
        String[] expectedResult = new String[]{"1","2","3"};
        assertTestCases(expectedResult,actual);
    }

    @Test
    public void testSpecialCaseWithPipeAsDelimiter(){
        String[] actual= inputProcessor.process("//*||\\n1*2|3|4");
        String[] expectedResult = new String[]{"1","2","3","4"};
        assertTestCases(expectedResult,actual);
    }

    /**
     * 9. Make sure you can also handle multiple delimiters with length longer than one character.
     */
    @Test
    public void testArrayWithCustomDelimiterWithMultiDelimitersWithAnyLength(){
        String[] actual= inputProcessor.process("//*|%%\\n1*2%%3*4");
        String[] expectedResult = new String[]{"1","2","3","4"};
        assertTestCases(expectedResult,actual);
    }

    private void assertTestCases(String[] expectedResult,String[] actual){
        assertNotNull(actual);
        assertEquals(expectedResult.length,actual.length);
        assertTrue(Arrays.equals(expectedResult, actual));
    }

}
