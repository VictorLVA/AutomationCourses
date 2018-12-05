package Factory;

import Results.SubtractionResult;
import Results.MultiplicationResult;
import Results.DivisionResult;
import Results.Result;
import Results.AdditionResult;

public class ResultsFactory {

    private static final String MESSAGE_CASE_ERROR = "Sorry. Something went wrong. Application will be closed.";

    private ResultsFactory() {
    }

    public static Result createResult(double firstNumber, double secondNumber, char operation) {
        switch (operation) {
            case '+':
                return new AdditionResult(firstNumber, secondNumber);
            case '-':
                return new SubtractionResult(firstNumber, secondNumber);
            case '*':
                return new MultiplicationResult(firstNumber, secondNumber);
            case '/':
                return new DivisionResult(firstNumber, secondNumber);
            default:
                throw new RuntimeException(MESSAGE_CASE_ERROR);
        }
    }
}