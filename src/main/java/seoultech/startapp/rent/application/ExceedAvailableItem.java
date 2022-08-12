package seoultech.startapp.rent.application;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class ExceedAvailableItem extends BusinessException {

    public ExceedAvailableItem(String message) {
        super(ErrorType.EXCEED_AVAILABLE_ITEM,message);
    }
}
