package seoultech.startapp.member.application;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberProfile;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.MemberStatus;

@Component
public class MemberFactory {

  // 회원가입 전 생성.
  public static Member preRegisterMember(RegisterCommand command){
    return Member.builder()
        .password(command.getAppPassword())
        .fcmToken(command.getFcmToken())
        .memberRole(MemberRole.MEMBER)
        .memberStatus(MemberStatus.PRE_CARD_AUTH)
        .memberProfile(MemberProfile.builder()
            .studentNo(command.getStudentNo())
            .name(command.getName())
            .department(command.getDepartment())
            .phoneNo(command.getPhoneNo())
            .memberShip(false)
            .build())
        .build();
  }
}
