package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class AlreadyExistMemberShipException extends BusinessException {

  public AlreadyExistMemberShipException(String message) {
    super(ErrorType.ALREADY_EXIST_MEMBERSHIP, message);
  }
}
