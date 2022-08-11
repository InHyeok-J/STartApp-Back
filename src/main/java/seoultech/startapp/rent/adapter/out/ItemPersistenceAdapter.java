package seoultech.startapp.rent.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.SaveItemPort;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

@Component
@RequiredArgsConstructor
public class ItemPersistenceAdapter implements LoadItemPort, SaveItemPort {

    private final JpaItemRepository jpaItemRepository;
    private final ItemMapper itemMapper;

    @Override
    public long countAllCategoryItems(ItemCategory itemCategory) {
        return jpaItemRepository.countAllByItemCategory(itemCategory);
    }

    @Override
    public long countAvailableFalseCategoryItems(ItemCategory itemCategory) {
        return jpaItemRepository.countAllByItemCategoryAndIsAvailableFalse(itemCategory);
    }

    @Override
    public void saveItem(Item item) {
        JpaItem jpaItem = itemMapper.mapToJpaItem(item);

        checkDuplicatedItemNo(jpaItem.getItemNo());

        jpaItemRepository.save(jpaItem);
    }

    private void checkDuplicatedItemNo(String itemNo){
        jpaItemRepository.findByItemNo(itemNo)
            .ifPresent(item -> {
                throw new DuplicatedItemNo("ItemNo이 중복됩니다. 다른 ItemNo을 사용해주세요");
            });
    }
}
