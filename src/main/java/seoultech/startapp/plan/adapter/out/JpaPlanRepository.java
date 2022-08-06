package seoultech.startapp.plan.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface JpaPlanRepository extends JpaRepository<JpaPlan,Long> {

    Page<JpaPlan> findAllByOrderByStartTimeDesc(Pageable pageable);
}
