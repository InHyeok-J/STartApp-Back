package seoultech.startapp.rent.adapter.out;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRentRepository extends JpaRepository<JpaRent,Long> {
  List<JpaRent> findAllByMemberId(Long memberId);
}
