package seoultech.startapp.rent.application;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotRentItemException extends BusinessException {

    public NotRentItemException(String message) {
        super(ErrorType.NOT_RENT_ITEM,message);
    }
}
