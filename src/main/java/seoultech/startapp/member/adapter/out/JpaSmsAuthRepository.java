package seoultech.startapp.member.adapter.out;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSmsAuthRepository extends JpaRepository<JpaSmsAuth, Long> {

  Long countBySmsTimeAfterAndPhoneNo(LocalDateTime smsTime, String phoneNo);
  Optional<JpaSmsAuth> findByAuthCodeAndPhoneNo(String authCode, String phoneNo);
  Optional<JpaSmsAuth> findFirstByPhoneNoOrderByIdDesc(String phoneNo);
}
