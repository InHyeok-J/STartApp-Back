package seoultech.startapp.member.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberStatus;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

  private Long memberId;
  private String studentNo;
  private String name;
  private String department;
  private String phoneNo;
  private Boolean memberShip;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private MemberStatus memberStatus;
  private String studentCardImageUrl;

  public static MemberResponse toDetailDto(Member member){
    return MemberResponse.builder()
        .memberId(member.getMemberId())
        .studentNo(member.getProfile().getStudentNo())
        .name(member.getProfile().getName())
        .department(member.getProfile().getDepartment())
        .phoneNo(member.getProfile().getPhoneNo() == null ? "" : member.getProfile().getPhoneNo())
        .memberShip(member.getProfile().getMemberShip())
        .createdAt(member.getCreatedAt())
        .updatedAt(member.getUpdatedAt())
        .memberStatus(member.getMemberStatus())
        .studentCardImageUrl(member.getStudentCardImage())
        .build();
  }

  public static MemberResponse toMyInfo(Member member){
    return MemberResponse.builder()
        .memberId(member.getMemberId())
        .studentNo(member.getProfile().getStudentNo())
        .name(member.getProfile().getName())
        .department(member.getProfile().getDepartment())
        .phoneNo(member.getProfile().getPhoneNo() == null ? "" : member.getProfile().getPhoneNo())
        .memberShip(member.getProfile().getMemberShip())
        .createdAt(member.getCreatedAt())
        .updatedAt(member.getUpdatedAt())
        .memberStatus(member.getMemberStatus())
        .build();
  }

  public static MemberResponse toSummaryDto(Member member){
    return MemberResponse.builder()
        .memberId(member.getMemberId())
        .studentNo(member.getProfile().getStudentNo())
        .name(member.getProfile().getName())
        .memberStatus(member.getMemberStatus())
        .build();
  }
}
