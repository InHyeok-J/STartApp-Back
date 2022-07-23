package seoultech.startapp.global.exception;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends BusinessException{

  public InvalidInputException(HttpStatus status) {
    super(status);
  }

  public InvalidInputException(String message, HttpStatus status) {
    super(message, status);
  }
}
