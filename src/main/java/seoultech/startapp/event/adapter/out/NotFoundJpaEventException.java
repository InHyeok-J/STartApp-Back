package seoultech.startapp.event.adapter.out;

import org.springframework.http.HttpStatus;
import seoultech.startapp.global.exception.BusinessException;

public class NotFoundJpaEventException extends BusinessException {

    public NotFoundJpaEventException(String message, HttpStatus status) {
        super(message, status);
    }
}