package seoultech.startapp.member.application;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import seoultech.startapp.member.domain.Member;

@Getter
@AllArgsConstructor
public class MemberPagingResponse {

  private List<MemberListResponse> member;
  private int totalPage;

  public static MemberPagingResponse toDto(List<Member> members, int totalPage){
    return new MemberPagingResponse(members.stream().map(MemberListResponse::toDto)
        .toList(), totalPage);
  }
}
