package seoultech.startapp.member.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.domain.Member;

@Component
class MemberMapper {

  Member mapToDomainMember(JpaMember jpaMember) {
    return new Member.Builder(jpaMember.getStudentNo(),
        jpaMember.getName(),
        jpaMember.getPassword(),
        jpaMember.getDepartment(),
        jpaMember.getPhoneNo(),
        jpaMember.getStudentStatus(),
        jpaMember.getMemberRole())
        .setMemberId(jpaMember.getId())
        .setCreateAt(jpaMember.getCreatedAt())
        .setUpdateAt(jpaMember.getUpdatedAt())
        .setFcmToken(jpaMember.getFcmToken())
        .build();
  }

  JpaMember mapToJpaMember(Member member) {
    return JpaMember.builder()
        .id(member.getMemberId() == null ? null : member.getMemberId())
        .studentNo(member.getStudentNo())
        .password(member.getPassword())
        .name(member.getName())
        .department(member.getDepartment())
        .phoneNo(member.getPhoneNo())
        .studentStatus(member.getStudentStatus())
        .fcmToken(member.getFcmToken() == null ? null : member.getFcmToken())
        .build();
  }
}
