package seoultech.startapp.member.adapter.in.dto;

import seoultech.startapp.member.application.port.in.command.StudentCardAuthCommand;

public record AdminStudentCardAuthRequest(boolean isAuth) {
  public StudentCardAuthCommand toCommand(Long memberId){
    return StudentCardAuthCommand.builder()
        .memberId(memberId)
        .isAuth(isAuth)
        .build();
  }
}
