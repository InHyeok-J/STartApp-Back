package seoultech.startapp.member.adapter.in.dto;


import org.springframework.web.multipart.MultipartFile;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;

public record RegisterMemberRequest(String studentNo, String appPassword, String name,
                                    String department, String fcmToken,
                                    MultipartFile file
                                    ) {
  public RegisterCommand toCommand(){
    return RegisterCommand.builder()
        .studentNo(studentNo)
        .appPassword(appPassword)
        .name(name)
        .department(department)
        .fcmToken(fcmToken)
        .file(file)
        .build();
  }
}
