package seoultech.startapp.event.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface JpaEventRepository extends JpaRepository<JpaEvent,Long> {

    List<JpaEvent> findAllByOrderByStartTimeAsc();
    Page<JpaEvent> findAllByOrderByStartTimeDesc(Pageable pageable);
}
