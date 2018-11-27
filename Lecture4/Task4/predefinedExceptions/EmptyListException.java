package predefinedExceptions;

public class EmptyListException extends Throwable {

    private String message;

    public EmptyListException(String message) {
        this.message = message;
    }

    public void getMessageAndExit() {
        System.out.println(this.message);
        System.exit(0);
    }
}
