package seoultech.startapp.member.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;

@Getter
public class Member {

  private Long memberId;

  private String studentNo;

  private String name;

  private String password;

  private String department;

  private String phoneNo;

  private String fcmToken;

  private String email;

  private Boolean memberShip;

  private StudentStatus studentStatus;

  private MemberRole memberRole;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder
  public Member(Long memberId, String studentNo, String name, String password,
      String department, String phoneNo, String fcmToken, String email, Boolean memberShip,
      StudentStatus studentStatus, MemberRole memberRole, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.memberId = memberId;
    this.studentNo = studentNo;
    this.name = name;
    this.password = password;
    this.department = department;
    this.phoneNo = phoneNo;
    this.fcmToken = fcmToken;
    this.email = email;
    this.memberShip = memberShip;
    this.studentStatus = studentStatus;
    this.memberRole = memberRole;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public TokenInfo createAccessTokenInfo() {
    return TokenInfo.accessTokenInfo(this.memberId, this.memberRole);
  }

  public void changePassword(String password){
    this.password = password;
  }

  public void isMemberShip(Boolean memberShip){
    this.memberShip = memberShip;
  }

  public void updateMemberInfo(UpdateMemberCommand command){
    this.name =command.getName();
    this.department = command.getDepartment();
    this.email = command.getEmail();
    this.phoneNo = command.getPhoneNo();
    this.studentStatus = command.getStudentStatus();
    this.memberShip = command.getMemberShip();
  }
}
