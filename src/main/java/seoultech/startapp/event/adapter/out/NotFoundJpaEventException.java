package seoultech.startapp.event.adapter.out;

import org.springframework.http.HttpStatus;
import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotFoundJpaEventException extends BusinessException {

    public NotFoundJpaEventException(String message) {
        super(ErrorType.NOT_FOUND_RESOURCE, message);
    }
}