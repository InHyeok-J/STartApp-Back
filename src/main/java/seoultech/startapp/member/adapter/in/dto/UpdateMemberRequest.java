package seoultech.startapp.member.adapter.in.dto;

import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;

public record UpdateMemberRequest(String name, String department, String phoneNo,
                                  Boolean memberShip) {

  public UpdateMemberCommand toUpdateCommand(Long memberId) {
    return UpdateMemberCommand.builder()
        .memberId(memberId)
        .name(name)
        .department(department)
        .phoneNo(phoneNo)
        .memberShip(memberShip)
        .build();
  }
}
