import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Factory.ResultsFactory;
import Helper.InputProcessing;
import Results.Result;

public class Task1L5 {

    private static final String MESSAGE_NUMBER1 = "Enter the 1st number: ";
    private static final String MESSAGE_NUMBER2 = "Enter the 2nd number: ";
    private static final String MESSAGE_OPERATION = "Enter the operation (+ or -, * or /): ";
    private static final String MESSAGE_DIVISION_BY_0 = "You cannot divide by 0";

    private static BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            double firstNumber = 0;
            double secondNumber = 0;
            char operation = ' ';
            while (!InputProcessing.isFirstNumberAssigned()) {
                System.out.print(MESSAGE_NUMBER1);
                String input = userInputReader.readLine();
                firstNumber = InputProcessing.getUserNumber(input);
            }
            while (!InputProcessing.isOperationAssigned()) {
                System.out.print(MESSAGE_OPERATION);
                String input = userInputReader.readLine();
                operation = InputProcessing.getUserOperation(input);
            }
            while (!InputProcessing.isSecondNumberAssigned()) {
                System.out.print(MESSAGE_NUMBER2);
                String input = userInputReader.readLine();
                secondNumber = InputProcessing.getUserNumber(input);
            }
            if (operation == '/' && secondNumber == 0) {
                System.out.println(MESSAGE_DIVISION_BY_0);
            } else {
                Result result = ResultsFactory.createResult(firstNumber, secondNumber, operation);
                System.out.println(result);
            }
            InputProcessing.setFlagsValue(false);
        }
    }
}