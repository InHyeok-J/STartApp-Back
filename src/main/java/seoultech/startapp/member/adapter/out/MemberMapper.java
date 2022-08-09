package seoultech.startapp.member.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.domain.Member;

@Component
class MemberMapper {

    Member mapToDomainMember(JpaMember jpaMember) {
        return Member.builder()
                     .memberId(jpaMember.getId())
                     .studentNo(jpaMember.getStudentNo())
                     .name(jpaMember.getName())
                     .password(jpaMember.getPassword())
                     .department(jpaMember.getDepartment())
                     .phoneNo(jpaMember.getPhoneNo())
                     .fcmToken(jpaMember.getFcmToken())
                     .emaill(jpaMember.getEmail())
                     .memberShip(jpaMember.getMemberShip())
                     .studentStatus(jpaMember.getStudentStatus())
                     .memberRole(jpaMember.getMemberRole())
                     .createdAt(jpaMember.getCreatedAt())
                     .updatedAt(jpaMember.getUpdatedAt())
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
                        .fcmToken(member.getFcmToken())
                        .email(member.getEmaill())
                        .memberShip(member.getMemberShip())
                        .build();
    }

    Page<Member> mapToDomainMemberPage(Page<JpaMember> jpaMemberPage){
        return jpaMemberPage.map(this::mapToDomainMember);
    }
}