package seoultech.startapp.member.adapter.out;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface JpaMemberRepository extends JpaRepository<JpaMember, Long> {

  Optional<JpaMember> findByStudentNo(String studentNo);
  boolean existsByStudentNo(String studentNo);
}
