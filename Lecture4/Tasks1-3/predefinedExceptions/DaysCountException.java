package predefinedExceptions;

public class DaysCountException extends Throwable {

    private String message;

    public DaysCountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}