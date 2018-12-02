package Helper;

public class CalcProcessing {

    public static void doCalc(double firstNumber, double secondNumber, char operation) {
        if (operation == '+') {
            System.out.println("The sum = " + Operations.getSum(firstNumber, secondNumber));
        }
        if (operation == '-') {
            System.out.println("The diff = " + Operations.getDiff(firstNumber, secondNumber));
        }
        if (operation == '*') {
            System.out.println("The der = " + Operations.getDer(firstNumber, secondNumber));
        }
        if (operation == '/' && secondNumber == 0) {
            System.out.println("You cannot divide by 0");
            return;
        }
        if (operation == '/') {
            System.out.println("The quo = " + Operations.getQuo(firstNumber, secondNumber));
        }
    }
}
