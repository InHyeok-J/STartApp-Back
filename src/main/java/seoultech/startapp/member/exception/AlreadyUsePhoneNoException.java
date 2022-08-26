package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class AlreadyUsePhoneNoException extends BusinessException {

  public AlreadyUsePhoneNoException(String message) {
    super(ErrorType.ALREADY_USE_PHONE, message);
  }
}
