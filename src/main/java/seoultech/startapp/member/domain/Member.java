package seoultech.startapp.member.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Member {

  private Long memberId;

  private String studentNo;

  private String name;

  private String password;

  private String department;

  private String phoneNo;

  private String fcmToken;

  private StudentStatus studentStatus;

  private MemberRole memberRole;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private Member(Builder builder) {
    this.memberId = builder.memberId;
    this.studentNo = builder.studentNo;
    this.name = builder.name;
    this.password = builder.password;
    this.department = builder.department;
    this.phoneNo = builder.phoneNo;
    this.fcmToken = builder.fcmToken;
    this.studentStatus = builder.studentStatus;
    this.memberRole = builder.memberRole;
    this.createdAt = builder.createdAt;
    this.updatedAt = builder.updateAt;
  }

  public TokenInfo createAccessTokenInfo() {
    return TokenInfo.accessTokenInfo(this.memberId, this.memberRole);
  }

  public static class Builder {

    //필수 값. studentNo, name, password, department, phoneNo, studentStatus
    private final String studentNo;
    private final String name;
    private final String password;
    private final String department;
    private final String phoneNo;
    private final StudentStatus studentStatus;
    private MemberRole memberRole;
    //선택 값  MemberId, createdAt, updatedAt, fcmToken;
    private Long memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String fcmToken;

    public Builder(String studentNo, String name, String password, String department,
        String phoneNo, StudentStatus studentStatus, MemberRole memberRole) {
      this.studentNo = studentNo;
      this.name = name;
      this.password = password;
      this.department = department;
      this.phoneNo = phoneNo;
      this.studentStatus = studentStatus;
      this.memberRole = memberRole;
    }

    public Builder setMemberId(Long memberId) {
      this.memberId = memberId;
      return this;
    }

    public Builder setCreateAt(LocalDateTime createAt) {
      this.createdAt = createAt;
      return this;
    }

    public Builder setUpdateAt(LocalDateTime updateAt) {
      this.updateAt = updateAt;
      return this;
    }

    public Builder setFcmToken(String fcmToken) {
      this.fcmToken = fcmToken;
      return this;
    }

    public Member build() {
      return new Member(this);
    }
  }
}
