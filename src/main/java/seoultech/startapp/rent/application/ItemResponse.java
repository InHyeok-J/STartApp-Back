package seoultech.startapp.rent.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.rent.domain.Item;

@Getter
public class ItemResponse {
    private Long itemId;

    private String itemCategory;

    private String itemNo;

    private Boolean isAvailable;

    @Builder
    public ItemResponse(Long itemId, String itemCategory, String itemNo, Boolean isAvailable) {
        this.itemId = itemId;
        this.itemCategory = itemCategory;
        this.itemNo = itemNo;
        this.isAvailable = isAvailable;
    }

    public static ItemResponse itemToItemResponse(Item item){
        return ItemResponse.builder()
            .itemId(item.getItemId())
            .itemCategory(item.getItemCategory().toString())
            .itemNo(item.getItemNo())
            .isAvailable(item.getIsAvailable())
            .build();
    }
}
