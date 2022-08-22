package seoultech.startapp.festival.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class RequireStampException extends BusinessException {

  public RequireStampException(String message) {
    super(ErrorType.NOT_PRIZED_STAMP,message);
  }
}
