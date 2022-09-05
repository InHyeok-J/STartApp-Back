package seoultech.startapp.rent.adapter.out;

import seoultech.startapp.member.adapter.out.JpaMember;

import java.util.Optional;

public interface RenterRepository {
  Optional<JpaMember> findByMemberId(Long memberId);
}
