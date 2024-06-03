package leader.board.demo.exception;

import org.springframework.stereotype.Component;

@Component
public class UserException extends RuntimeException{
   private String message;

    public UserException(String message) {
        this.message = message;
    }

    public UserException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
