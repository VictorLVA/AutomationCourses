package Helper;

public class InputProcessing {

    private InputProcessing() {
    }

    private static final String MESSAGE_NEED_PROPER_VALUE = "Try to enter a proper value";
    private static final String MESSAGE_NEED_PROPER_OPERATION = "Try to enter a proper operation";
    private static final String REGEX_DOUBLE = "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$";
    private static final String REGEX_OPERATIONS = "[-+*/]";

    private static boolean isFirstNumberAssigned = false;
    private static boolean isOperationAssigned = false;
    private static boolean isSecondNumberAssigned = false;


    public static boolean isFirstNumberAssigned() {
        return isFirstNumberAssigned;
    }

    public static boolean isOperationAssigned() {
        return isOperationAssigned;
    }

    public static boolean isSecondNumberAssigned() {
        return isSecondNumberAssigned;
    }


    public static void setFlagsValue(boolean value) {
        isFirstNumberAssigned = value;
        isOperationAssigned = value;
        isSecondNumberAssigned = value;
    }

    public static boolean isQuitNeeded(String input) {
        return ("quit".equalsIgnoreCase(input));
    }

    public static double getUserNumber(String input) {
        double number = 0;
        if (isQuitNeeded(input)) {
            System.exit(0);
        }
        if (input.matches(REGEX_DOUBLE)) {
            number = Double.parseDouble(input);
            if (!isFirstNumberAssigned) {
                isFirstNumberAssigned = true;
            } else {
                isSecondNumberAssigned = true;
            }
        } else {
            System.out.print(MESSAGE_NEED_PROPER_VALUE + ". ");
        }
        return number;
    }

    public static char getUserOperation(String input) {
        char operation = ' ';
        if (isQuitNeeded(input)) {
            System.exit(0);
        }
        if (input.matches(REGEX_OPERATIONS)) {
            operation = input.charAt(0);
            isOperationAssigned = true;
        } else {
            System.out.print(MESSAGE_NEED_PROPER_OPERATION + ". ");
        }
        return operation;
    }
}