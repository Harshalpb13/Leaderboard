package leader.board.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserHandlerException {


    @ExceptionHandler(NoUserPresent.class)
    public ResponseEntity<String> NoUserPresent(NoUserPresent noUserPresent){
        return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> UserNotFoundExceptionHandler(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>("No User Present",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<String> alreadyExistsExceptionHandler(AlreadyExists alreadyExists){
        return new ResponseEntity<>("User is allready present",HttpStatus.BAD_REQUEST);
    }
}

