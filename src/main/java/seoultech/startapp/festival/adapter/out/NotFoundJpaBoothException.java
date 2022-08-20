package seoultech.startapp.festival.adapter.out;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotFoundJpaBoothException extends BusinessException {

  public NotFoundJpaBoothException(String message) {
    super(ErrorType.NOT_FOUND_RESOURCE, message);
  }
}
