package seoultech.startapp.festival.adapter.out;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStampRepository extends JpaRepository<JpaStamp, Long> {

  Optional<JpaStamp> findByMemberId(Long memberId);
}
