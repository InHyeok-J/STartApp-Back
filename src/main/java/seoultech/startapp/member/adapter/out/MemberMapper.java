package seoultech.startapp.member.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberProfile;

@Component
class MemberMapper {

    Member mapToDomainMember(JpaMember jpaMember) {
        return Member.builder()
                     .memberId(jpaMember.getId())
                     .password(jpaMember.getPassword())
                     .fcmToken(jpaMember.getFcmToken())
                     .memberRole(jpaMember.getMemberRole())
                     .createdAt(jpaMember.getCreatedAt())
                     .updatedAt(jpaMember.getUpdatedAt())
                     .memberProfile(MemberProfile.builder()
                         .studentNo(jpaMember.getStudentNo())
                         .name(jpaMember.getName())
                         .department(jpaMember.getDepartment())
                         .phoneNo(jpaMember.getPhoneNo())
                         .email(jpaMember.getEmail())
                         .studentStatus(jpaMember.getStudentStatus())
                         .memberShip(jpaMember.getMemberShip())
                         .build())
                     .build();
    }

    JpaMember mapToJpaMember(Member member) {
        return JpaMember.builder()
                        .id(member.getMemberId() == null ? null : member.getMemberId())
                        .studentNo(member.getProfile().getStudentNo())
                        .password(member.getPassword())
                        .name(member.getProfile().getName())
                        .department(member.getProfile().getDepartment())
                        .phoneNo(member.getProfile().getPhoneNo())
                        .studentStatus(member.getProfile().getStudentStatus())
                        .fcmToken(member.getFcmToken())
                        .email(member.getProfile().getEmail())
                        .memberShip(member.getProfile().getMemberShip())
                        .createdAt(member.getCreatedAt())
                        .updatedAt(member.getUpdatedAt())
                        .build();
    }

    Page<Member> mapToDomainMemberPage(Page<JpaMember> jpaMemberPage){
        return jpaMemberPage.map(this::mapToDomainMember);
    }
}