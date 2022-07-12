package seoultech.startapp.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException{

  private final HttpStatus status;

  public BusinessException(HttpStatus status) {
    this.status = status;
  }

  public BusinessException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
