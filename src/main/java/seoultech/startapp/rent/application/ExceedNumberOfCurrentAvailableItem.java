package seoultech.startapp.rent.application;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class ExceedNumberOfCurrentAvailableItem extends BusinessException {

    public ExceedNumberOfCurrentAvailableItem(String message) {
        super(ErrorType.EXCEED_NUMBER_OF_CURRENT_AVAILABLE_ITEM,message);
    }
}
