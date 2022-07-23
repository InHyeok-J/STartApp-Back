package seoultech.startapp.member.exception;

import org.springframework.http.HttpStatus;
import seoultech.startapp.global.exception.BusinessException;

public class NotLoginMemberException extends BusinessException {

  public NotLoginMemberException(HttpStatus status) {
    super(status);
  }

  public NotLoginMemberException(String message, HttpStatus status) {
    super(message, status);
  }
}
