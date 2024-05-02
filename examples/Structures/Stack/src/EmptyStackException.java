public class EmptyStackException extends IllegalStateException {

    public EmptyStackException() {
    }

    public EmptyStackException(String s) {
        super(s);
    }

    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyStackException(Throwable cause) {
        super(cause);
    }
}
