package predefinedExceptions;

public class YesNoAnswerException extends Throwable {

    private String message;

    public YesNoAnswerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
