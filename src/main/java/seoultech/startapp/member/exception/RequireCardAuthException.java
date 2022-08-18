package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class RequireCardAuthException extends BusinessException {

  public RequireCardAuthException(String message) {
    super(ErrorType.REQUIRE_CARD_AUTH, message);
  }
}
