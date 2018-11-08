import java.io.*;

public class Task3L1 {

    private static final String MESSAGE_MODES = " (1-Common, 2-Star, 3-withTryCatchExceptions): ";
    private static final String MESSAGE_NUMBER1 = "Enter the 1st number: ";
    private static final String MESSAGE_NUMBER2 = "Enter the 2nd number: ";
    private static final String MESSAGE_OPERATION = "Enter the operation";
    private static final String MESSAGE_NEED_PROPER_VALUE = "Try to enter a proper value";
    private static final String MESSAGE_NEED_PROPER_OPERATION = "Try to enter a proper operation";
    private static final String REGEX_INT = "^[-+]?\\d+";
    private static final String REGEX_DOUBLE = "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$";

    private static BufferedReader reader = new BufferedReader(new InputStreamReader ( System.in ));

    public static void main (String[] args) throws Exception {
        short mode = 0;
        System.out.print("Please select a mode" + MESSAGE_MODES);
        while (mode == 0) {
            String input = reader.readLine();
            if ("quit".equals (input)) {
                return;
            }
            if (input.matches ("[1-3]")) {
                mode = Short.parseShort(input);
            }
            else {
                System.out.print ("Please select a proper mode!" + MESSAGE_MODES);
            }
        }
        if (mode == 1) {
            while (true) {
                long firstNumber;
                long secondNumber;
                char operation;
                System.out.println();
                while (true) {
                    System.out.print(MESSAGE_NUMBER1);
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    if (input.matches(REGEX_INT)) {
                        firstNumber = Long.parseLong(input);
                        break;
                    }
                    else {
                        System.out.print(MESSAGE_NEED_PROPER_VALUE + " (INT). ");
                    }
                }
                while (true) {
                    System.out.print(MESSAGE_OPERATION + " (+ or -): ");
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    if (input.equals("+") || input.equals("-")) {
                        operation = input.charAt(0);
                        break;
                    }
                    else {
                        System.out.print(MESSAGE_NEED_PROPER_OPERATION + ". ");
                    }
                }
                while (true) {
                    System.out.print(MESSAGE_NUMBER2);
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    if (input.matches(REGEX_INT)) {
                        secondNumber = Long.parseLong(input);
                        break;
                    }
                    else System.out.print(MESSAGE_NEED_PROPER_VALUE + " (INT). ");
                }
                doCalc(firstNumber , secondNumber , operation);
            }
        }
        if (mode == 2) {
            while (true) {
                double firstNumber;
                double secondNumber;
                char operation;
                System.out.println();
                while (true) {
                    System.out.print(MESSAGE_NUMBER1);
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    if (input.matches(REGEX_DOUBLE)) {
                        firstNumber = Double.parseDouble(input);
                        break;
                    }
                    else {
                        System.out.print(MESSAGE_NEED_PROPER_VALUE + ". ");
                    }
                }
                while (true) {
                    System.out.print(MESSAGE_OPERATION + " (+ or -, * or /): ");
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
                        operation = input.charAt(0);
                        break;
                    }
                    else {
                        System.out.print(MESSAGE_NEED_PROPER_OPERATION + ". ");
                    }
                }
                while (true) {
                    System.out.print(MESSAGE_NUMBER2);
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    if (input.matches(REGEX_DOUBLE)) {
                        secondNumber = Double.parseDouble(input);
                        break;
                    }
                    else {
                        System.out.print(MESSAGE_NEED_PROPER_VALUE + ". ");
                    }
                }
                doCalc(firstNumber , secondNumber , operation);
            }
        }
        if (mode == 3) {
            while (true) {
                double firstNumber;
                double secondNumber;
                char operation;
                System.out.println();
                while (true) {
                    System.out.print(MESSAGE_NUMBER1);
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    else {
                        try {
                            firstNumber = Double.parseDouble(input);
                            break;
                        }
                        catch (Exception e) {
                            System.out.print(MESSAGE_NEED_PROPER_VALUE + ". ");
                        }
                    }
                }
                while (true) {
                    System.out.print(MESSAGE_OPERATION + " (+ or -, * or /): ");
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
                        operation = input.charAt(0);
                        break;
                    }
                    else {
                        System.out.print(MESSAGE_NEED_PROPER_OPERATION + ". ");
                    }
                }
                while (true) {
                    System.out.print(MESSAGE_NUMBER2);
                    String input = reader.readLine();
                    if ("quit".equals(input)) {
                        return;
                    }
                    else {
                        try {
                            secondNumber = Double.parseDouble(input);
                            break;
                        }
                        catch (Exception e) {
                            System.out.print("Try to enter a proper value. ");
                        }
                    }
                }
                doCalc(firstNumber , secondNumber , operation);
            }
        }
    }

    private static void doCalc (long firstNumber, long secondNumber, char operation) {
        if (operation == '+') {
            System.out.println("The sum = " + (firstNumber + secondNumber));
        }
        else {
            System.out.println("The diff = " + (firstNumber - secondNumber));
        }
    }

    private static void doCalc (double firstNumber, double secondNumber, char operation) {
        if (operation == '+') {
            System.out.println("The sum = " + (firstNumber + secondNumber));
        }
        if (operation == '-') {
            System.out.println("The diff = " + (firstNumber - secondNumber));
        }
        if (operation == '*') {
            System.out.println("The der = " + (firstNumber * secondNumber));
        }
        if (operation == '/' && secondNumber == 0) {
            System.out.println("You cannot divide by 0");
            return;
        }
        if (operation == '/') {
            System.out.println("The quo = " + (firstNumber / secondNumber));
        }
    }
}