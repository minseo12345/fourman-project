package fourman.project1.exception.traffic;

public class TrafficErrorException extends RuntimeException{

    public TrafficErrorException() {
        super("k6 command error");
    }

    public TrafficErrorException(String message) {
        super(message);
    }

    public TrafficErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
