package seoultech.startapp.member.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.member.domain.Member;

@Getter
@Builder
public class MemberListResponse {

  private Long memberId;
  private String studentNo;
  private String name;

  public static MemberListResponse toDto(Member member){
    return MemberListResponse.builder()
        .memberId(member.getMemberId())
        .studentNo(member.getStudentNo())
        .name(member.getName())
        .build();
  }
}
