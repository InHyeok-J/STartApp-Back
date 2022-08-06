package seoultech.startapp.plan.adapter.out;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

class NotFoundJpaPlanException extends BusinessException {

    public NotFoundJpaPlanException(String message) {
        super(ErrorType.NOT_FOUND_RESOURCE, message);
    }
}