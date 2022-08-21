package seoultech.startapp.rent.adapter.out;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotFoundRentException extends BusinessException {
  public NotFoundRentException(String message) {
    super(ErrorType.NOT_FOUND_RESOURCE, message);
  }
}
