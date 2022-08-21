package seoultech.startapp.rent.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotRentableItemException extends BusinessException {

  public NotRentableItemException(String message) {
    super(ErrorType.NOT_RENTABLE_ITEM, message);
  }
}
