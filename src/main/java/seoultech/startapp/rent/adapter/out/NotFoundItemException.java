package seoultech.startapp.rent.adapter.out;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotFoundItemException extends BusinessException {
    public NotFoundItemException(String message) {
        super(ErrorType.NOT_FOUND_RESOURCE,message);
    }
}
