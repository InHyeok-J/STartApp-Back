package seoultech.startapp.member.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements SaveMemberPort , LoadMemberPort {

  private final JpaMemberRepository jpaMemberRepository;
  private final MemberMapper memberMapper;

  @Override
  public Member loadByMemberId(Long memberId) {
    JpaMember jpaMember = jpaMemberRepository.findById(memberId)
        .orElseThrow(()-> new NotFoundJpaMemberException("member를 찾을 수 없습니다"));
    return memberMapper.mapToDomainMember(jpaMember);
  }

  @Override
  public Member loadByStudentNo(String studentNo) {
    JpaMember jpaMember = jpaMemberRepository.findByStudentNo(studentNo)
        .orElseThrow(()-> new NotFoundJpaMemberException("member를 찾을 수 없습니다"));
    return memberMapper.mapToDomainMember(jpaMember);
  }

  @Override
  public Member save(Member member) {
    JpaMember jpaMember = memberMapper.mapToJpaMember(member);
    JpaMember savedMember = jpaMemberRepository.save(jpaMember);
    return memberMapper.mapToDomainMember(savedMember);
  }

  @Override
  public boolean existByStudentNo(String studentNo) {
    return jpaMemberRepository.existsByStudentNo(studentNo);
  }
}