public class NullInputException extends Exception {
    @Override
    public String getMessage() {
        return "Can't handle null currency";
    }
}
