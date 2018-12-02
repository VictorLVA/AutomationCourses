public class Task1L5 {

    private static final String MESSAGE_NUMBER1 = "Enter the 1st number: ";
    private static final String MESSAGE_NUMBER2 = "Enter the 2nd number: ";
    private static final String MESSAGE_OPERATION = "Enter the operation (+ or -, * or /): ";

    public static void main(String[] args) {
        while (true) {
            double firstNumber = 0;
            double secondNumber = 0;
            char operation = ' ';
            while (!Helper.InputProcessing.isInputAssigned()) {
                System.out.print(MESSAGE_NUMBER1);
                firstNumber = Helper.InputProcessing.getUserNumber();
            }
            Helper.InputProcessing.setInputAssigned(false);
            while (!Helper.InputProcessing.isInputAssigned()) {
                System.out.print(MESSAGE_OPERATION);
                operation = Helper.InputProcessing.getUserOperation();
            }
            Helper.InputProcessing.setInputAssigned(false);
            while (!Helper.InputProcessing.isInputAssigned()) {
                System.out.print(MESSAGE_NUMBER2);
                secondNumber = Helper.InputProcessing.getUserNumber();
            }
            Helper.InputProcessing.setInputAssigned(false);
            Helper.CalcProcessing.doCalc(firstNumber, secondNumber, operation);
        }
    }
}