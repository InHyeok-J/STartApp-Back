package seoultech.startapp.member.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.adapter.out.RenterRepository;

@Component
@RequiredArgsConstructor
public class RenterRepositoryImpl implements RenterRepository {

  private final JpaMemberRepository jpaMemberRepository;

  @Override
  public JpaMember findByMemberId(Long memberId) {
    return jpaMemberRepository.findById(memberId)
        .orElseThrow(()-> new NotFoundJpaMemberException("해당 멤버를 찾을 수 없습니다."));
  }
}
