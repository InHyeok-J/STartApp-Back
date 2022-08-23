package seoultech.startapp.festival.adapter.out;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLineUpRepository extends JpaRepository<JpaLineUp, Long> {

  List<JpaLineUp> findAllByOrderByLineUpTimeAsc();
}
