package seoultech.startapp.member.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

  @Test
  @DisplayName("Member 빌더 테스트")
  public void memberBuilderTest() throws Exception {
    //given
    String studentNo = "17101245";
    String name = "조인혁";
    String password = "qwer1234";
    String department = "컴퓨터공학과";
    String phoneNo = "010-9999-9999";
    StudentStatus studentStatus = StudentStatus.STUDENT;
    MemberRole memberRole = MemberRole.MEMBER;

    //when
    Member member = new Member.Builder(studentNo,name,password,department,phoneNo,studentStatus,memberRole)
        .setMemberId(1L).build();
    //then
    assertEquals(memberRole,member.getMemberRole());
    assertEquals(1L , member.getMemberId());
    assertNull(member.getFcmToken()); // FCM 은 셋팅 안하면 Null
  }
}