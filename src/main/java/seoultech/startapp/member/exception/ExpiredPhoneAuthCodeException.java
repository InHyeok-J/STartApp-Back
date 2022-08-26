package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class ExpiredPhoneAuthCodeException extends BusinessException {

  public ExpiredPhoneAuthCodeException(String message) {
    super(ErrorType.EXPIRED_PHONE_AUTH_CODE, message);
  }
}
