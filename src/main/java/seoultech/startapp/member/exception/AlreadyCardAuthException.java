package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class AlreadyCardAuthException extends BusinessException {

  public AlreadyCardAuthException(String message) {
    super(ErrorType.ALREADY_CARD_AUTH, message);
  }
}
