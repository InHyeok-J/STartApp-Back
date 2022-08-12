package seoultech.startapp.rent.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import seoultech.startapp.rent.domain.ItemCategory;

import java.util.Optional;

interface JpaItemRepository extends JpaRepository<JpaItem,Long> {

    long countAllByItemCategory(ItemCategory itemCategory);

    long countAllByItemCategoryAndIsAvailableFalse(ItemCategory itemCategory);

    Optional<JpaItem> findByItemNo(String itemNo);

}
