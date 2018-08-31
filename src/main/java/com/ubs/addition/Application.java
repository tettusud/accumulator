package com.ubs.addition;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculatorService calculatorService =new CalculatorService();
        while (scanner.hasNextLine()) {
            System.out.println(calculatorService.accumalate(scanner.nextLine()));
        }
    }
}
