package seoultech.startapp.banner.adapter.out;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBannerRepository extends JpaRepository<JpaBanner, Long> {
  List<JpaBanner> findAllByIsDeletedFalseOrderByPriorityAsc();

  Page<JpaBanner> findAllByOrderByIsDeletedAsc(PageRequest pageRequest);


}
