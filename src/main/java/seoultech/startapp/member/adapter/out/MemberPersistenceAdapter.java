package seoultech.startapp.member.adapter.out;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.out.DeleteMemberPort;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.MemberStatus;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements SaveMemberPort, LoadMemberPort, DeleteMemberPort {

  private final JpaMemberRepository jpaMemberRepository;
  private final MemberMapper memberMapper;

  @Override
  public Member loadByMemberId(Long memberId) {
    JpaMember jpaMember = jpaMemberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundJpaMemberException("member를 찾을 수 없습니다"));
    return memberMapper.mapToDomainMember(jpaMember);
  }

  @Override
  public Member loadByMemberIdNullable(Long memberId) {
    return jpaMemberRepository.findById(memberId)
        .map(memberMapper::mapToDomainMember).orElse(null);
  }

  @Override
  public Member loadByStudentNo(String studentNo) {
    JpaMember jpaMember = jpaMemberRepository.findByStudentNo(studentNo)
        .orElseThrow(() -> new NotFoundJpaMemberException("member를 찾을 수 없습니다"));
    return memberMapper.mapToDomainMember(jpaMember);
  }

  @Override
  public Member loadByStudentNoNullable(String studentNo) {
    Optional<JpaMember> jpaMember = jpaMemberRepository.findByStudentNo(studentNo);

    return jpaMember.map(memberMapper::mapToDomainMember).orElse(null);
  }

  @Override
  public void deleteMember(Member member) {
    JpaMember jpaMember = memberMapper.mapToJpaMember(member);
    jpaMemberRepository.delete(jpaMember);
  }

  @Override
  public Member save(Member member) {
    JpaMember jpaMember = memberMapper.mapToJpaMember(member);
    JpaMember savedMember = jpaMemberRepository.save(jpaMember);
    return memberMapper.mapToDomainMember(savedMember);
  }

  @Override
  public boolean existByStudentNoAndNotLeave(String studentNo) {
    return jpaMemberRepository.existsByStudentNoAndMemberStatusNot(studentNo, MemberStatus.LEAVE);
  }

  @Override
  public Page<Member> loadNotPreAutMemberByPaging(PageRequest pageRequest) {
    Page<JpaMember> jpaPageMembers = jpaMemberRepository.findAllByMemberRoleAndMemberStatusNotOrderByIdAsc(
        MemberRole.MEMBER, MemberStatus.PRE_CARD_AUTH, pageRequest);

    return memberMapper.mapToDomainMemberPage(jpaPageMembers);
  }

  @Override
  public Page<Member> loadPreAuthMemberByPaging(PageRequest pageRequest) {
    Page<JpaMember> jpaPageMembers = jpaMemberRepository.findAllByMemberRoleAndMemberStatusOrderByIdAsc(
        MemberRole.MEMBER,  MemberStatus.PRE_CARD_AUTH,pageRequest);

    return memberMapper.mapToDomainMemberPage(jpaPageMembers);
  }
}
