package seoultech.startapp.member.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.StudentStatus;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
        .studentNo(member.getProfile().getStudentNo())
        .name(member.getProfile().getName())
        .department(member.getProfile().getDepartment())
        .phoneNo(member.getProfile().getPhoneNo())
        .email(member.getProfile().getEmail())
        .isMemberShip(member.getProfile().getMemberShip())
        .studentStatus(member.getProfile().getStudentStatus())
        .createdAt(member.getCreatedAt())
        .updatedAt(member.getUpdatedAt())
        .build();
  }

  public static MemberResponse toSummaryDto(Member member){
    return MemberResponse.builder()
        .memberId(member.getMemberId())
        .studentNo(member.getProfile().getStudentNo())
        .name(member.getProfile().getName())
        .build();
  }
}
