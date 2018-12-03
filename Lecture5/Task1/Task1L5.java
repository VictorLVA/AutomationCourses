import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task1L5 {

    private static final String MESSAGE_NUMBER1 = "Enter the 1st number: ";
    private static final String MESSAGE_NUMBER2 = "Enter the 2nd number: ";
    private static final String MESSAGE_OPERATION = "Enter the operation (+ or -, * or /): ";
    private static final String MESSAGE_DIVISION_BY_0 = "You cannot divide by 0";

    private static BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        while (true) {
            double firstNumber = 0;
            double secondNumber = 0;
            char operation = ' ';
            while (!Helper.InputProcessing.isInputAssigned()) {
                System.out.print(MESSAGE_NUMBER1);
                firstNumber = Helper.InputProcessing.getUserNumber(userInputReader);
            }
            Helper.InputProcessing.setInputAssigned(false);
            while (!Helper.InputProcessing.isInputAssigned()) {
                System.out.print(MESSAGE_OPERATION);
                operation = Helper.InputProcessing.getUserOperation(userInputReader);
            }
            Helper.InputProcessing.setInputAssigned(false);
            while (!Helper.InputProcessing.isInputAssigned()) {
                System.out.print(MESSAGE_NUMBER2);
                secondNumber = Helper.InputProcessing.getUserNumber(userInputReader);
            }
            Helper.InputProcessing.setInputAssigned(false);
            if (operation == '/' && secondNumber == 0) {
                System.out.println(MESSAGE_DIVISION_BY_0);
            } else {
                System.out.println
                        (Helper.CalcProcessing.getResultTextByOperation(operation) +
                                 Helper.CalcProcessing.getResultByOperation(firstNumber, secondNumber, operation));
            }
        }
    }
}