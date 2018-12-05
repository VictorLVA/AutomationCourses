package Results;

public class AdditionResult extends Result {

    private static final String RESULT_TEXT = "The sum = ";

    public AdditionResult(double firstNumber, double secondNumber) {
        super(firstNumber + secondNumber);
    }

    @Override
    public String getText() {
        return RESULT_TEXT;
    }

    @Override
    public String toString() {
        return RESULT_TEXT + super.toString();
    }
}
