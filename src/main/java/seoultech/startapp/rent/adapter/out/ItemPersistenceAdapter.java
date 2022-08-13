package seoultech.startapp.rent.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.application.port.out.CountItemPort;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.SaveItemPort;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

@Component
@RequiredArgsConstructor
public class ItemPersistenceAdapter implements LoadItemPort, SaveItemPort, CountItemPort {

    private final JpaItemRepository jpaItemRepository;
    private final ItemMapper itemMapper;

    @Override
    public long countByCategory(ItemCategory itemCategory) {
        return jpaItemRepository.countAllByItemCategory(itemCategory);
    }

    @Override
    public long countNotAvailableByCategory(ItemCategory itemCategory) {
        return jpaItemRepository.countAllByItemCategoryAndIsAvailableFalse(itemCategory);
    }

    @Override
    public void saveItem(Item item) {
        JpaItem jpaItem = itemMapper.mapToJpaItem(item);
        jpaItemRepository.save(jpaItem);
    }

    @Override
    public Page<Item> loadAllItemByPaging(PageRequest pageRequest) {
        Page<JpaItem> jpaItemPage = jpaItemRepository.findAllByOrderByItemNoAsc(pageRequest);
        return itemMapper.mapToDomainEventPage(jpaItemPage);
    }

    @Override
    public Item loadById(Long itemId) {
        JpaItem jpaItem = jpaItemRepository.findById(itemId)
                                           .orElseThrow(() -> new NotFoundItemException("itemId에 해당하는 item이 없습니다."));
        return itemMapper.mapToDomainItem(jpaItem);
    }

    @Override
    public Boolean existsByItemNo(String itemNo) {
        return jpaItemRepository.existsByItemNo(itemNo);
    }
}
