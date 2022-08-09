package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.StudentStatus;

@ExtendWith(MockitoExtension.class)
class MemberGetServiceTest {

  @Mock
  LoadMemberPort loadMemberPort;

  @InjectMocks
  MemberGetService memberGetService;

  @Test
  @DisplayName("회원 리스트 조회 성공")
  public void getMemberListTestSuccess() throws Exception {
    //given
    int page = 0;
    int count = 3;
    PageRequest pageRequest = PageRequest.of(page, count);
    Page<Member> pageMember = mockPageMember(count);
    given(loadMemberPort.loadByPaging(pageRequest)).willReturn(pageMember);
    //when
    MemberPagingResponse memberList = memberGetService.getMemberList(page, count);

    //then
    assertEquals(memberList.getMemberList().size(),count);
  }

  @Test
  @DisplayName("멤버 상세 조회 성공")
  public void getMemberOneSuccess() throws Exception {
    //given
    Long memberId = 1L;
    given(loadMemberPort.loadByMemberId(memberId)).willReturn(mockMember(memberId));
    //when
    MemberResponse memberOne = memberGetService.getMemberOne(memberId);
    //then
    assertEquals(memberOne.getMemberId(), memberId);
  }

  private Page<Member> mockPageMember(int count){
    List<Member> members = new ArrayList<>();
    for(Long i = 1L; i<= count; i++){
      Member member = mockMember(i);
      members.add(member);
    }

    return new PageImpl(members);
  }

  private Member mockMember(Long memberId){
    return Member.builder()
        .memberId(memberId)
        .studentNo("학번")
        .name("이름")
        .phoneNo("010-")
        .studentStatus(StudentStatus.STUDENT)
        .memberShip(true)
        .memberRole(MemberRole.MEMBER)
        .department("학과")
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}