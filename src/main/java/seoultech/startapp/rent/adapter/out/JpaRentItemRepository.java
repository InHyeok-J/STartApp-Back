package seoultech.startapp.rent.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRentItemRepository extends JpaRepository<JpaRentItem,JpaRentItemId> {

    //Boolean existsByInJpaRentAndJpaItem(List<JpaRentItem> jpaRentItems);
}
