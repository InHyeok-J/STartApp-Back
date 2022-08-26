package seoultech.startapp.member.application;


import seoultech.startapp.member.application.port.in.command.RegisterCommand;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberProfile;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.MemberStatus;


public class MemberFactory {
  // 회원가입 전 생성.
  public static Member preRegisterMember(RegisterCommand command, String encodedPassword,
      String cardImage) {
    return Member.builder()
        .password(encodedPassword)
        .fcmToken(command.getFcmToken())
        .memberRole(MemberRole.MEMBER)
        .memberStatus(MemberStatus.PRE_CARD_AUTH)
        .studentCardImage(cardImage)
        .memberProfile(MemberProfile.builder()
            .studentNo(command.getStudentNo())
            .name(command.getName())
            .department(command.getDepartment())
            .memberShip(false)
            .phoneNo(command.getPhoneNo())
            .build())
        .build();
  }
}
