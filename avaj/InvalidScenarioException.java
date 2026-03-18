package avaj;

public class InvalidScenarioException extends Exception {

    public InvalidScenarioException(String message) {
        super(message);
    }

    public InvalidScenarioException(String message, Throwable cause) {
        super(message, cause);
    }
}
