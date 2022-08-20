package seoultech.startapp.festival.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class AlreadyStampException extends BusinessException {

  public AlreadyStampException(String message) {
    super(ErrorType.ALREADY_STAMP, message);
  }
}
