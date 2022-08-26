package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotMatchPhoneAuthException extends BusinessException {

  public NotMatchPhoneAuthException(String message) {
    super(ErrorType.NOT_MATCH_PHONE_AUTH, message);
  }
}
