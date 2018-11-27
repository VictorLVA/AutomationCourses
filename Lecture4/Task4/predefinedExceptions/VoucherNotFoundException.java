package predefinedExceptions;

public class VoucherNotFoundException extends Throwable {

    private String message;

    public VoucherNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}