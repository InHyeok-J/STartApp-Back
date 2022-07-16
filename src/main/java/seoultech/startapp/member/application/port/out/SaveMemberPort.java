package seoultech.startapp.member.application.port.out;

import seoultech.startapp.member.domain.Member;

public interface SaveMemberPort {
  void save(Member member);
}
