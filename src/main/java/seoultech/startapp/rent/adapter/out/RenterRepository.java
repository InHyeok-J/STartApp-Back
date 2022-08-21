package seoultech.startapp.rent.adapter.out;

import seoultech.startapp.member.adapter.out.JpaMember;

public interface RenterRepository {
  JpaMember findByMemberId(Long memberId);
}
