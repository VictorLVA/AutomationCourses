package Results;

public class Result {

    private static final String RESULT_TEXT = "Result = ";

    private double result;

    Result(double value) {
        this.result = value;
    }

    public double getResult() {
        return result;
    }

    public String getText() {
        return RESULT_TEXT;
    }

    public String toString() {
        return RESULT_TEXT + result;
    }
}
