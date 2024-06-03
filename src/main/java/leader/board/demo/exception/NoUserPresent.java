package leader.board.demo.exception;

public class NoUserPresent extends RuntimeException{

    public NoUserPresent() {
    }

    public NoUserPresent(String message) {
        super(message);
    }

    public NoUserPresent(String message, Throwable cause) {
        super(message, cause);
    }
}
