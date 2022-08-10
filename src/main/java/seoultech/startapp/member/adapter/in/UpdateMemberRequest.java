package seoultech.startapp.member.adapter.in;

import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;

public record UpdateMemberRequest(String name, String department, String email, String phoneNo,
                                  String studentStatus, Boolean memberShip) {

  public UpdateMemberCommand toUpdateCommand(Long memberId) {
    return UpdateMemberCommand.builder()
        .memberId(memberId)
        .name(name)
        .department(department)
        .email(email)
        .phoneNo(phoneNo)
        .studentStatus(studentStatus)
        .memberShip(memberShip)
        .build();
  }
}
