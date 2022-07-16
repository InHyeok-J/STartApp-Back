package seoultech.startapp.member.adapter.out;

import org.springframework.http.HttpStatus;
import seoultech.startapp.global.exception.BusinessException;

public class NotFoundJpaMemberException extends BusinessException {

  public NotFoundJpaMemberException(String message, HttpStatus status) {
    super(message, status);
  }
}
