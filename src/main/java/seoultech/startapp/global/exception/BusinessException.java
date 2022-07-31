package seoultech.startapp.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorType errorType;

  public BusinessException(ErrorType errorType) {
    this.errorType = errorType;
  }

  public BusinessException(ErrorType errorType, String message) {
    super(message);
    this.errorType = errorType;
  }
}
