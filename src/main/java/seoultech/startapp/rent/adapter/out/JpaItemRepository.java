package seoultech.startapp.rent.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import seoultech.startapp.rent.domain.ItemCategory;

interface JpaItemRepository extends JpaRepository<JpaItem,Long> {

    long countAllByItemCategory(ItemCategory itemCategory);

    long countAllByItemCategoryAndIsAvailableFalse(ItemCategory itemCategory);
    
    Page<JpaItem> findAllByOrderByItemNoAsc(Pageable pageable);

    Boolean existsByItemNo(String itemNo);
}
