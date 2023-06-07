package lmx.toxicdating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotCreatedException extends RuntimeException{
    public EntityNotCreatedException(String message) {
        super(message);
    }
}
