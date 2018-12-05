package Results;

public class MultiplicationResult extends Result {

    private static final String RESULT_TEXT = "The product = ";

    public MultiplicationResult(double firstNumber, double secondNumber) {
        super(firstNumber * secondNumber);
    }

    @Override
    public String toString() {
        return RESULT_TEXT + super.toString();
    }
}
