package seoultech.startapp.rent.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import seoultech.startapp.rent.domain.ItemCategory;

import java.util.List;

interface JpaItemRepository extends JpaRepository<JpaItem,Long> {

    long countAllByItemCategory(ItemCategory itemCategory);

    long countAllByItemCategoryAndIsAvailableFalse(ItemCategory itemCategory);
    Boolean existsByItemNo(String itemNo);

    List<JpaItem> findAllByIdIn(List<Long> itemIds);
    long countAllByItemCategoryAndIsAvailableTrue(ItemCategory itemCategory);
}
