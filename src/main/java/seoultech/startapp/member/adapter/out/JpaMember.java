package seoultech.startapp.member.adapter.out;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.common.BaseTimeJpaEntity;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.StudentStatus;

@Getter
@NoArgsConstructor
@Entity(name = "member")
class JpaMember extends BaseTimeJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(name = "student_no", unique = true, nullable = false)
  private String studentNo;

  @Column( nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String department;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private Boolean memberShip;

  @Column(name ="phone_no" ,nullable = false)
  private String phoneNo;

  @Column(name = "fcm_token")
  private String fcmToken;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StudentStatus studentStatus;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MemberRole memberRole = MemberRole.MEMBER;

  @Builder
  public JpaMember(Long id ,String studentNo, String password, String name, String department,
      String phoneNo, StudentStatus studentStatus,String fcmToken, String email, boolean memberShip) {
    this.id = id;
    this.studentNo = studentNo;
    this.password = password;
    this.name = name;
    this.department = department;
    this.phoneNo = phoneNo;
    this.studentStatus = studentStatus;
    this.fcmToken = fcmToken;
    this.email = email;
    this.memberShip = memberShip;
  }

  public void setFcmToken(String token){
    this.fcmToken = token;
  }

  public void setMemberShip(boolean isMemberShip){
    this.memberShip = isMemberShip;
  }
}
