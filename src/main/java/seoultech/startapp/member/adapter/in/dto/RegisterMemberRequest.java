package seoultech.startapp.member.adapter.in.dto;


import seoultech.startapp.member.application.port.in.command.RegisterCommand;

public record RegisterMemberRequest(String studentNo, String appPassword, String name,
                                    String department, String phoneNo, String fcmToken
                                    ) {
  public RegisterCommand toCommand(){
    return RegisterCommand.builder()
        .phoneNo(phoneNo)
        .studentNo(studentNo)
        .appPassword(appPassword)
        .name(name)
        .department(department)
        .fcmToken(fcmToken)
        .build();
  }
}
