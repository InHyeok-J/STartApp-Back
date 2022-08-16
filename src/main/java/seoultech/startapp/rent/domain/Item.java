package seoultech.startapp.rent.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Item {

    private Long itemId;

    private ItemCategory itemCategory;

    private String itemNo;

    private Boolean isAvailable;

    private Boolean isRentable;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Builder
    public Item(Long itemId,
                ItemCategory itemCategory,
                String itemNo,
                Boolean isAvailable,
                Boolean isRentable,
                LocalDateTime createAt,
                LocalDateTime updateAt) {
        this.itemId = itemId;
        this.itemCategory = itemCategory;
        this.itemNo = itemNo;
        this.isAvailable = isAvailable;
        this.isRentable = isRentable;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public void changeAvailable(Boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void rent(Boolean isRentable){
        this.isRentable = isRentable;
    }
}
