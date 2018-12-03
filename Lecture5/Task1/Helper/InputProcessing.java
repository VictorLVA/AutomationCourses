package Helper;

import java.io.BufferedReader;
import java.io.IOException;

public class InputProcessing {

    private static final String MESSAGE_NEED_PROPER_VALUE = "Try to enter a proper value";
    private static final String MESSAGE_NEED_PROPER_OPERATION = "Try to enter a proper operation";
    private static final String MESSAGE_EXCEPTION = "Sorry, something went wrong";
    private static final String REGEX_DOUBLE = "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$";
    private static final String REGEX_OPERATIONS = "[-+*/]";

    private static boolean isInputAssigned = false;

    public static boolean isInputAssigned() {
        return isInputAssigned;
    }

    public static void setInputAssigned(boolean isInputAssigned) {
        InputProcessing.isInputAssigned = isInputAssigned;
    }

    public static boolean isQuitNeeded(String input) {
        return ("quit".equalsIgnoreCase(input));
    }

    public static double getUserNumber(BufferedReader userInputReader) {
        String input = null;
        double number = 0;
        try {
            input = userInputReader.readLine();
        } catch (IOException ex) {
            System.out.println(MESSAGE_EXCEPTION);
            System.exit(0);
        }
        if (isQuitNeeded(input)) {
            System.exit(0);
        }
        if (input.matches(REGEX_DOUBLE)) {
            number = Double.parseDouble(input);
            isInputAssigned = true;
        } else {
            System.out.print(MESSAGE_NEED_PROPER_VALUE + ". ");
        }
        return number;
    }

    public static char getUserOperation(BufferedReader userInputReader) {
        String input = null;
        char operation = ' ';
        try {
            input = userInputReader.readLine();
        } catch (IOException ex) {
            System.out.println(MESSAGE_EXCEPTION);
            System.exit(0);
        }
        if (isQuitNeeded(input)) {
            System.exit(0);
        }
        if (input.matches(REGEX_OPERATIONS)) {
            operation = input.charAt(0);
            isInputAssigned = true;
        } else {
            System.out.print(MESSAGE_NEED_PROPER_OPERATION + ". ");
        }
        return operation;
    }
}
