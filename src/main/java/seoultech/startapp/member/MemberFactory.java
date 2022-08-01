package seoultech.startapp.member;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberRole;

@Component
public class MemberFactory {

  // 회원가입 전 생성.
  public static Member preRegisterMember(RegisterCommand command){
    return Member.builder()
        .studentNo(command.getStudentNo())
        .password(command.getAppPassword())
        .name(command.getName())
        .department(command.getDepartment())
        .phoneNo(command.getPhoneNo())
        .fcmToken(command.getFcmToken())
        .studentStatus(command.getStudentStatus())
        .emaill(command.getEmail())
        .memberRole(MemberRole.MEMBER)
        .build();
  }
}
