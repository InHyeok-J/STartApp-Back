package seoultech.startapp.helper.domain;

import java.time.LocalDateTime;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberProfile;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.StudentStatus;

public class MockDomainMember {

  public static Member generalMockMemberByStudentNo(String studentNo){
    return Member.builder()
        .memberId(1L)
        .memberRole(MemberRole.MEMBER)
        .password("password")
        .fcmToken("fcmToken")
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .memberProfile(MemberProfile.builder()
            .studentNo(studentNo)
            .name("학생이름")
            .department("학과")
            .phoneNo("010-9999-9999")
            .email("email@email.ocm")
            .studentStatus(StudentStatus.STUDENT)
            .memberShip(false)
            .build()
        )
        .build();
  }

  public static Member generalMockMemberByMemberId(Long memberId){
    return Member.builder()
        .memberId(memberId)
        .memberRole(MemberRole.MEMBER)
        .password("password")
        .fcmToken("fcmToken")
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .memberProfile(MemberProfile.builder()
            .studentNo("학번")
            .name("학생이름")
            .department("학과")
            .phoneNo("010-9999-9999")
            .email("email@email.ocm")
            .studentStatus(StudentStatus.STUDENT)
            .memberShip(false)
            .build()
        )
        .build();
  }
}
