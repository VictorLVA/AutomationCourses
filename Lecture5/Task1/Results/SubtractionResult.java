package Results;

public class SubtractionResult extends Result {

    private static final String RESULT_TEXT = "The difference = ";

    public SubtractionResult(double firstNumber, double secondNumber) {
        super(firstNumber - secondNumber);
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
