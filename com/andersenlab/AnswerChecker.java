package com.andersenlab;

import java.util.Scanner;

//Make up an algorithm
//        If the entered number is greater than 7, then print “Hello”
//        If the entered name matches “John”, then output “Hello, John”, if not, then output "There is no such name"
//        There is a numeric array at the input, it is necessary to output array elements that are multiples of 3

import java.util.Scanner;

public class AnswerChecker {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {

                System.out.print("Enter a number, text, or a numeric array (comma-separated): ");
                String input = scanner.nextLine();

                if (input.matches("\\d+")) {
                    System.out.println("processing number");

                    int number = Integer.parseInt(input);

                    if (number > 7) {
                        System.out.println("Hello");
                    }
                } else if (input.equals("John")) {
                    System.out.println("Hello, John");
                } else {
                    System.out.println("There is no such name");
                }

                String[] inputArray = input.split(",");
                for (String element : inputArray) {
                    try {
                        int num = Integer.parseInt(element.trim());
                        if (num % 3 == 0) {
                            System.out.println("Multiple of 3: " + num);
                        }
                    } catch (NumberFormatException e) {
                        // Ignore non-numeric elements in the input
                    }
                }
            }
        }
    }
}



