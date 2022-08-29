package seoultech.startapp.member.adapter.in.dto;

import seoultech.startapp.member.application.port.in.command.NotLoginPasswordChangeCommand;

public record NotLoginPasswordChangeRequest(String studentNo, String password) {

  public NotLoginPasswordChangeCommand toCommand(){
    return NotLoginPasswordChangeCommand.builder()
        .studentNo(studentNo)
        .password(password)
        .build();
  }
}
