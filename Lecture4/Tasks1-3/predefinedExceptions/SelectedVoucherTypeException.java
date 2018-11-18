package predefinedExceptions;

public class SelectedVoucherTypeException extends Throwable {

    private String message;

    public SelectedVoucherTypeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
