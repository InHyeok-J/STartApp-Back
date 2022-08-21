package seoultech.startapp.rent.adapter.out;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRentItemRepository extends JpaRepository<JpaRentItem,JpaRentItemId> {

    //Boolean existsByInJpaRentAndJpaItem(List<JpaRentItem> jpaRentItems);
  List<JpaRentItem> findAllByJpaRent(JpaRent jpaRent);
}
