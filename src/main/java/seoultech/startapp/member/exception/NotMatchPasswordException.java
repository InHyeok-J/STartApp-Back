package seoultech.startapp.member.exception;

import org.springframework.http.HttpStatus;
import seoultech.startapp.global.exception.BusinessException;

public class NotMatchPasswordException extends BusinessException {

  public NotMatchPasswordException(String message, HttpStatus status) {
    super(message, status);
  }
}
