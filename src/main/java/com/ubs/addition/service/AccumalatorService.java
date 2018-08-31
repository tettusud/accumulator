package com.ubs.addition.service;

import com.ubs.addition.command.AdditionCommand;
import com.ubs.addition.convertors.Convertor;
import com.ubs.addition.convertors.StringIntegerArrayConvertor;
import com.ubs.addition.processor.InputProcessor;
import com.ubs.addition.processor.impl.DelimiterProcessor;

public class AccumalatorService implements CalculatorService<String,Integer> {

    InputProcessor<String, String> inputProcessor;
    Convertor<String, Integer> convertor = new StringIntegerArrayConvertor();

    /**
     *
     */
    public AccumalatorService() {
        this.inputProcessor = new DelimiterProcessor();
    }

    /**
     * @param numbers
     * @return
     */
    public Integer execute(String numbers) {
        //1 process input
        String[] processedInput = processInput(numbers);
        // covert input
        Integer[] convertedInput = convertInput(processedInput);
        // process the command
        Integer result =null;
        return apply(convertedInput);
    }

    private String[] processInput(String numbers) {
        return this.inputProcessor.process(numbers);
    }

    private Integer[] convertInput(String[] numbers) {
        return convertor.convert(numbers);
    }

    private int apply(Integer[] numbers) {
        return new AdditionCommand(numbers).execute();
    }

    public void setInputProcessor(InputProcessor<String, String> inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public void setConvertor(Convertor<String, Integer> convertor) {
        this.convertor = convertor;
    }
}
