package Results;

public class DivisionResult extends Result {

    private static final String RESULT_TEXT = "The quotient = ";

    public DivisionResult(double firstNumber, double secondNumber) {
        super(firstNumber / secondNumber);
    }

    @Override
    public String toString() {
        return RESULT_TEXT + super.toString();
    }
}
