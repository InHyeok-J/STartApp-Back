package seoultech.startapp.global.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationFailException extends BusinessException{

  public AuthenticationFailException(HttpStatus status) {
    super(status);
  }

  public AuthenticationFailException(String message, HttpStatus status) {
    super(message, status);
  }
}
