package seoultech.startapp.member.application;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.StudentStatus;

@Getter
@Builder
public class MemberResponse {

  private Long memberId;
  private String studentNo;
  private String name;
  private String department;
  private String phoneNo;
  private String email;
  private Boolean isMemberShip;
  private StudentStatus studentStatus;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static MemberResponse toDto(Member member){
    return MemberResponse.builder()
        .memberId(member.getMemberId())
        .studentNo(member.getStudentNo())
        .name(member.getName())
        .department(member.getDepartment())
        .phoneNo(member.getPhoneNo())
        .email(member.getEmail())
        .isMemberShip(member.getMemberShip())
        .studentStatus(member.getStudentStatus())
        .createdAt(member.getCreatedAt())
        .updatedAt(member.getUpdatedAt())
        .build();
  }
}
