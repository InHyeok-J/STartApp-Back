package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import seoultech.startapp.rent.application.port.in.command.ItemCommand;
import seoultech.startapp.rent.domain.ItemCategory;

@Getter
class ItemRequest {

    private ItemCategory itemCategory;

    private String itemNo;

    private Boolean isAvailable;

    public ItemCommand toItemCommand(){
        return ItemCommand.builder()
            .itemCategory(this.itemCategory)
            .itemNo(this.itemNo)
            .isAvailable(this.isAvailable)
            .build();
    }
}
