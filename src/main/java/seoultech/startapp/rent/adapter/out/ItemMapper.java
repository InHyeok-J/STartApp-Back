package seoultech.startapp.rent.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.domain.Item;

@Component
class ItemMapper {

    public Item mapToDomainItem(JpaItem jpaItem){
        return Item.builder()
            .itemNo(jpaItem.getItemNo())
            .itemCategory(jpaItem.getItemCategory())
            .itemId(jpaItem.getId())
            .isAvailable(jpaItem.getIsAvailable())
            .build();
    }

    public JpaItem mapToJpaItem(Item item){
        return JpaItem.builder()
            .id(item.getItemId() == null ? null : item.getItemId())
            .itemCategory(item.getItemCategory())
            .itemNo(item.getItemNo())
            .isAvailable(item.getIsAvailable())
            .build();
    }

    Page<Item> mapToDomainEventPage(Page<JpaItem> jpaItemPage){
        return jpaItemPage.map(jpaItem -> mapToDomainItem(jpaItem));
    }
}
