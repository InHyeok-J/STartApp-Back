package seoultech.startapp.member.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;
import seoultech.startapp.member.exception.LeaveMemberException;
import seoultech.startapp.member.exception.RequireCardAuthException;

@Getter
public class Member {

  private Long memberId;

  private String password;

  private MemberProfile profile;

  private String fcmToken;

  private MemberRole memberRole;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private MemberStatus memberStatus;

  private String studentCardImage;

  @Builder
  public Member(Long memberId, String password,String fcmToken,
       MemberRole memberRole, LocalDateTime createdAt,
      LocalDateTime updatedAt, MemberProfile memberProfile,
    MemberStatus memberStatus, String studentCardImage
  ) {
    this.memberId = memberId;
    this.password = password;
    this.fcmToken = fcmToken;
    this.memberRole = memberRole;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.profile = memberProfile;
    this.memberStatus = memberStatus;
    this.studentCardImage = studentCardImage;
  }

  public TokenInfo createAccessTokenInfo() {
    return TokenInfo.accessTokenInfo(this.memberId, this.memberRole);
  }

  public void cardApprove(){
    this.memberStatus = MemberStatus.POST_CARD_AUTH;
  }

  public void canLoginValidation(){
    switch (this.memberStatus){
      case PRE_CARD_AUTH -> throw new RequireCardAuthException("학생증 인증 중인 회원입니다.");
      case LEAVE -> throw new LeaveMemberException("탈퇴한 회원 정보입니다.");
    }
  }


  public void leaveMember(){
    this.memberStatus = MemberStatus.LEAVE;
  }


  public void changeMemberShip(Boolean memberShip){
    this.profile.changeMemberShip(memberShip);
  }

  public void updateProfile(MemberProfile memberProfile){
    this.profile = memberProfile;
  }
}
