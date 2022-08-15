package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class SeoulTechAuthenticationFailException extends BusinessException {

  public SeoulTechAuthenticationFailException(
      String message) {
    super(ErrorType.AUTHORIZATION_FAIL, message);
  }
}
