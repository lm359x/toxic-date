package lmx.toxicdating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserNotInChatException extends RuntimeException{
    public UserNotInChatException(String message) {
        super(message);
    }
}
