package com.ubs.addition;

import com.ubs.addition.service.AccumalatorService;
import com.ubs.addition.service.CalculatorService;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculatorService<String,Integer> accumalatorService =new AccumalatorService();
        print("Enter input:");
        while (scanner.hasNextLine()) {
            try{
                print(accumalatorService.execute(scanner.nextLine()));
            }catch(IllegalArgumentException iae){
                print(iae.getMessage());
            }finally {
                print("Enter Input:");
            }
        }
    }

    public static void print(Object message){
        System.out.println(message);
    }
}
