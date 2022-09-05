package seoultech.startapp.member.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.adapter.out.RenterRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RenterRepositoryImpl implements RenterRepository {

  private final JpaMemberRepository jpaMemberRepository;

  @Override
  public Optional<JpaMember> findByMemberId(Long memberId) {
    return jpaMemberRepository.findById(memberId);
  }
}
