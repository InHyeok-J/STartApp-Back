package seoultech.startapp.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberProfile {
  private String studentNo;
  private String name;
  private String department;
  private String phoneNo;
  private String email;
  private StudentStatus studentStatus;
  private Boolean memberShip;

  @Builder
  public MemberProfile(String studentNo, String name, String department, String phoneNo,
      String email, StudentStatus studentStatus, Boolean memberShip) {
    this.studentNo = studentNo;
    this.name = name;
    this.department = department;
    this.phoneNo = phoneNo;
    this.email = email;
    this.studentStatus = studentStatus;
    this.memberShip = memberShip;
  }

  public void changeMemberShip(Boolean memberShip){
    this.memberShip = memberShip;
  }

}
