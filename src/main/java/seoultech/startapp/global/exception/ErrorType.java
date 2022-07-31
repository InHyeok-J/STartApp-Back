package seoultech.startapp.global.exception;

import lombok.Getter;


@Getter
public enum ErrorType {
  //공통 사항
  INVALID_INPUT("ST001", 400),
  INVALID_HEADER("ST002", 400),
  INVALID_JWT_FAIl("ST010", 401),
  EXPIRED_JWT_FAIL("ST011", 401),
  AUTHORIZATION_FAIL("ST030", 403),
  NOT_FOUND_PATH("ST040", 404),
  NOT_FOUND_RESOURCE("ST041", 404),

  //예외 처리 사항
  NOT_MATCH_PASSWORD("ST050", 409),
  NOT_LOGIN_MEMBER("ST051",409),
  NOT_MATCH_LOGIN_INFO("ST052", 409),
  INTERNAL_SERVER_ERROR("ST999",500);

  private final String errorType;
  private final int statusCode;

  ErrorType(String errorType, int statusCode) {
    this.errorType = errorType;
    this.statusCode = statusCode;
  }
}
