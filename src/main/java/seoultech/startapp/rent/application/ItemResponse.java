package seoultech.startapp.rent.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.rent.domain.Item;

import java.time.LocalDateTime;

@Getter
public class ItemResponse {
    private Long itemId;

    private String itemCategory;

    private String itemNo;

    private Boolean isAvailable;

    private Boolean isRentable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public ItemResponse(Long itemId,
                        String itemCategory,
                        String itemNo,
                        Boolean isAvailable,
                        Boolean isRentable,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt) {
        this.itemId = itemId;
        this.itemCategory = itemCategory;
        this.itemNo = itemNo;
        this.isAvailable = isAvailable;
        this.isRentable = isRentable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public static ItemResponse itemToItemResponse(Item item){
        return ItemResponse.builder()
            .itemId(item.getItemId())
            .itemCategory(item.getItemCategory().toString())
            .itemNo(item.getItemNo())
            .isAvailable(item.getIsAvailable())
            .isRentable(item.getIsRentable())
            .createdAt(item.getCreateAt())
            .updatedAt(item.getUpdateAt())
            .build();
    }
}
