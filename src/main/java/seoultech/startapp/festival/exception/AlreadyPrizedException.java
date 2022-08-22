package seoultech.startapp.festival.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class AlreadyPrizedException extends BusinessException {

  public AlreadyPrizedException(String message) {
    super(ErrorType.ALREADY_PRIZED,message);
  }
}
