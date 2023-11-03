package com.andersenlab;

import java.util.Scanner;
import java.util.Stack;

public class Validator {
    public static boolean isBracketSequenceValid(String input) {
        Stack<Character> stack = new Stack<>();

        for (var currentChar : input.toCharArray()) {
            if ("({[".contains(String.valueOf(currentChar))) {
                stack.push(currentChar);
            } else if (")}]".contains(String.valueOf(currentChar))) {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if (!areBracketsMatching(top, currentChar)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean areBracketsMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    public static String suggestBracketCorrection(String input) {
        Stack<Character> stack = new Stack<>();
        StringBuilder suggestedCorrection = new StringBuilder(input);

        for (int i = 0; i < suggestedCorrection.length(); i++) {
            char currentChar = suggestedCorrection.charAt(i);

            if ("({[".contains(String.valueOf(currentChar))) {
                stack.push(currentChar);
            } else if (")}]".contains(String.valueOf(currentChar))) {
                if (stack.isEmpty()) {
                    // Suggest inserting an opening bracket
                    suggestedCorrection.insert(i, getMatchingOpeningBracket(currentChar));
                } else {
                    char top = stack.pop();
                    if (!areBracketsMatching(top, currentChar)) {
                        // Suggest replacing the mismatched bracket
                        suggestedCorrection.setCharAt(i, getMatchingBracket(top));
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            // Suggest inserting closing brackets for unmatched opening brackets
            suggestedCorrection.append(getMatchingBracket(stack.pop()));
        }

        return suggestedCorrection.toString();
    }

    private static char getMatchingOpeningBracket(char close) {
        switch (close) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return ' ';
        }
    }

    private static char getMatchingBracket(char open) {
        switch (open) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            default:
                return ' ';
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter a bracket sequence (or 'exit' to quit): ");
                var input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                if (isBracketSequenceValid(input)) {
                    System.out.println("The bracket sequence is valid.");
                } else {
                    String suggestedCorrection = suggestBracketCorrection(input);
                    System.out.println("The bracket sequence is not valid.");
                    System.out.println("Suggested correction: " + suggestedCorrection);
                }
            }
        }
    }
}
