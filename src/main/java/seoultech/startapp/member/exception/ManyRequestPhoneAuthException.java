package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class ManyRequestPhoneAuthException extends BusinessException {

  public ManyRequestPhoneAuthException(String message) {
    super(ErrorType.MANY_REQUEST_PHONE_AUTH, message);
  }
}
