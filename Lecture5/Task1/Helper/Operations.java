package Helper;

public class Operations {

    private Operations() {
    }

    public static double getSum(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    public static double getDiff(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }

    public static double getDer(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }

    public static double getQuo(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }
}
