package seoultech.startapp.member.application;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberProfile;
import seoultech.startapp.member.domain.MemberRole;

@Component
public class MemberFactory {

  // 회원가입 전 생성.
  public static Member preRegisterMember(RegisterCommand command){
    return Member.builder()
        .password(command.getAppPassword())
        .fcmToken(command.getFcmToken())
        .memberRole(MemberRole.MEMBER)
        .memberProfile(MemberProfile.builder()
            .studentNo(command.getStudentNo())
            .name(command.getName())
            .department(command.getDepartment())
            .phoneNo(command.getPhoneNo())
            .email(command.getEmail())
            .studentStatus(command.getStudentStatus())
            .memberShip(false)
            .build())
        .build();
  }
}
