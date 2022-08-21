package seoultech.startapp.rent.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotConfirmRentException extends BusinessException {

  public NotConfirmRentException(String message) {
    super(ErrorType.NOT_CONFIRM_RENT, message);
  }
}
