package Helper;

public class CalcProcessing {

    public static double getResultByOperation(double firstNumber, double secondNumber, char operation) {
        if (operation == '+') {
            return Operations.getSum(firstNumber, secondNumber);
        } else if (operation == '-') {
            return Operations.getDiff(firstNumber, secondNumber);
        } else if (operation == '*') {
            return Operations.getDer(firstNumber, secondNumber);
        } else {
            return Operations.getQuo(firstNumber, secondNumber);
        }
    }

    public static String getResultTextByOperation(char operation) {
        if (operation == '+') {
            return "The sum = ";
        } else if (operation == '-') {
            return "The diff = ";
        } else if (operation == '*') {
            return "The der = ";
        } else {
            return "The quo = ";
        }
    }
}
