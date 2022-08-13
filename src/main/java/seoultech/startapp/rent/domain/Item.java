package seoultech.startapp.rent.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Item {

    private Long itemId;

    private ItemCategory itemCategory;

    private String itemNo;

    private Boolean isAvailable;

    @Builder
    public Item(Long itemId, ItemCategory itemCategory, String itemNo, Boolean isAvailable) {
        this.itemId = itemId;
        this.itemCategory = itemCategory;
        this.itemNo = itemNo;
        this.isAvailable = isAvailable;
    }

    public void changeAvailable(Boolean isAvailable){
        this.isAvailable = isAvailable;
    }
}
