package seoultech.startapp.banner.adapter.out;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotFoundJpaBannerException extends BusinessException {

  public NotFoundJpaBannerException(String message) {
    super(ErrorType.NOT_FOUND_RESOURCE, message);
  }
}
