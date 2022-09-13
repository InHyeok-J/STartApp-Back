package seoultech.startapp.festival.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotStartFestivalException extends BusinessException {

  public NotStartFestivalException(String message) {
    super(ErrorType.NOT_START_FESTIVAL,message);
  }
}
