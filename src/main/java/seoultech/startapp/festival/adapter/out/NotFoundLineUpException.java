package seoultech.startapp.festival.adapter.out;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotFoundLineUpException extends BusinessException {

  public NotFoundLineUpException(String message) {
    super(ErrorType.NOT_FOUND_PATH, message);
  }
}
