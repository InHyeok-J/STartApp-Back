package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.validation.constraints.NotNull;

@Getter
public class ItemCommand extends SelfValidator<ItemCommand> {

    @NotNull
    private ItemCategory itemCategory;

    @NotNull
    private String itemNo;

    @NotNull
    private Boolean isAvailable;

    @Builder
    public ItemCommand(ItemCategory itemCategory, String itemNo, Boolean isAvailable) {
        this.itemCategory = itemCategory;
        this.itemNo = itemNo;
        this.isAvailable = isAvailable;
        this.validateSelf();
    }

    public Item toDomainItem(){
        return Item.builder()
            .itemNo(itemNo)
            .itemCategory(itemCategory)
            .isAvailable(isAvailable)
            .build();
    }
}
