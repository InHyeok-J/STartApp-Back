package seoultech.startapp.member.adapter.out;

import java.time.LocalDateTime;
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
import seoultech.startapp.member.domain.MemberStatus;

@Getter
@NoArgsConstructor
@Entity(name = "member")
public class JpaMember extends BaseTimeJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(name = "student_no", unique = true, nullable = false)
  private String studentNo;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String department;

  @Column(nullable = false)
  private Boolean memberShip;

  @Column(name = "phone_no", nullable = true)
  private String phoneNo;

  @Column(name = "fcm_token")
  private String fcmToken;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MemberRole memberRole = MemberRole.MEMBER;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "member_status")
  private MemberStatus memberStatus;

  @Column(name = "student_card_image")
  private String studentCardImage;

  @Builder
  public JpaMember(Long id, String studentNo, String password, String name,
      String department, String phoneNo, String fcmToken, boolean memberShip,
      LocalDateTime createdAt, LocalDateTime updatedAt, MemberStatus memberStatus,
      String studentCardImage
  ) {
    this.id = id;
    this.studentNo = studentNo;
    this.password = password;
    this.name = name;
    this.department = department;
    this.phoneNo = phoneNo;
    this.fcmToken = fcmToken;
    this.memberShip = memberShip;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.memberStatus = memberStatus;
    this.studentCardImage = studentCardImage;
  }

  public void setFcmToken(String token) {
    this.fcmToken = token;
  }

  public void setMemberShip(boolean isMemberShip) {
    this.memberShip = isMemberShip;
  }
}
