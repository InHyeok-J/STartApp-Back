package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotMatchLoginInfoException extends BusinessException {

  public NotMatchLoginInfoException(String message) {
    super(ErrorType.NOT_MATCH_LOGIN_INFO, message);
  }
}
