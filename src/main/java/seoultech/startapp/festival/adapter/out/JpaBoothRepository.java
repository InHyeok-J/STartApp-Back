package seoultech.startapp.festival.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoothRepository extends JpaRepository<JpaBooth, Long> {

  Page<JpaBooth> findAllByOrderByIdDesc(PageRequest pageRequest);
}
