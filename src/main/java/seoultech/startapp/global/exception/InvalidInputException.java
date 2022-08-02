package seoultech.startapp.global.exception;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends BusinessException{

  public InvalidInputException(String message) {
    super(ErrorType.INVALID_INPUT, message);
  }
}
