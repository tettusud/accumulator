package com.ubs.addition.processor.impl;

import com.ubs.addition.processor.InputProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * This handles input string processing and extracts the input splitting by computed delimiter pattern
 */
public class DelimiterProcessor implements InputProcessor<String, String> {

    private static final String DELIMITER_CHANGE_EXTRACT_PATTERN = "^(//)(.*)(\\\\n)(.*)";
    //Pattern hasDelimiterChangePattern = Pattern.compile(DELIMITER_CHANGE_PATTERN);
    private static final String DEFAULT_DELIMITER_PATTERN = ",|\\\\n";
    // delimiter holder
    private String delimiter = null;

    public DelimiterProcessor() {
        this.delimiter = DEFAULT_DELIMITER_PATTERN;
    }


    @Override
    public String[] process(String numbers) {
        if (numbers.isEmpty()) {
            return new String[]{};
        }

        String inputStr = extract(numbers);
        String[] inputNumbers = parse(inputStr);
        return inputNumbers;
    }


    private String replaceSpecialCharacter(String numbers) {
        return numbers.replaceAll("[-\\[\\]{}()*+?.\\\\\\\\^$#|\\\\s]", "\\\\$0");
    }

    /***
     * check if there is any pattern specified then return the input to process
     * @param numbers
     * @return
     */
    private String extract(String numbers) {
        Pattern delimiterChangePattern = Pattern.compile(DELIMITER_CHANGE_EXTRACT_PATTERN);
        Matcher hasDelimiterChangePatternMatcher = delimiterChangePattern.matcher(numbers);
        if (hasDelimiterChangePatternMatcher.find()) {
            //update the delimiter
            this.delimiter = replaceSpecialCharacter(hasDelimiterChangePatternMatcher.group(2));
            return hasDelimiterChangePatternMatcher.group(4);
        }
        return numbers;
    }

    private String[] parse(String numbers) {
        //handle or conditions in special case ,replace all pipes but first
        this.delimiter = this.delimiter.replaceFirst(Pattern.quote("\\|"),"|");
        return numbers.split(this.delimiter);
    }
}
