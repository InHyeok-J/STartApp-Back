package seoultech.startapp.member.adapter.out;

import org.springframework.http.HttpStatus;
import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotFoundJpaMemberException extends BusinessException {

  public NotFoundJpaMemberException(String message) {
    super(ErrorType.NOT_FOUND_RESOURCE, message);
  }
}
