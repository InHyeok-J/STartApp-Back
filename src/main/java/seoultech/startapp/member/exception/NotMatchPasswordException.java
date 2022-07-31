package seoultech.startapp.member.exception;

import org.springframework.http.HttpStatus;
import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotMatchPasswordException extends BusinessException {

  public NotMatchPasswordException(String message) {
    super(ErrorType.NOT_MATCH_PASSWORD, message);
  }
}
