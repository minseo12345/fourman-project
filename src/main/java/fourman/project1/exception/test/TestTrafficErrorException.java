package fourman.project1.exception.test;

public class TestTrafficErrorException extends RuntimeException{

    public TestTrafficErrorException() {
        super("k6 command error");
    }

    public TestTrafficErrorException(String message) {
        super(message);
    }
}
