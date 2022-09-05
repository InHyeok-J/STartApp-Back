package seoultech.startapp.member.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.LeaveMemberUseCase;
import seoultech.startapp.member.application.port.out.DeleteMemberPort;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;

@Service
@RequiredArgsConstructor
public class LeaveMemberService implements LeaveMemberUseCase {
  private final DeleteMemberPort deleteMemberPort;

  private final RedisCachePort redisCachePort;

  @Transactional
  @Override
  public void leave(Long memberId) {
    deleteMemberPort.deleteById(memberId);
    redisCachePort.deleteByKey("MEMBER-"+memberId);
  }
}
