package seoultech.startapp.member.adapter.in.dto;

import seoultech.startapp.member.application.port.in.command.MemberPasswordChangeCommand;

public record MemberPasswordChangeRequest(String currentPassword, String newPassword) {

  public MemberPasswordChangeCommand toCommand(Long memberId){
    return MemberPasswordChangeCommand.builder()
        .memberId(memberId)
        .currentPassword(currentPassword)
        .newPassword(newPassword)
        .build();
  }
}
