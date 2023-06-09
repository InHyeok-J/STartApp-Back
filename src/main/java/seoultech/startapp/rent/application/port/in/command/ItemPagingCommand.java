package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Getter
public class ItemPagingCommand extends SelfValidator<ItemPagingCommand> {


    private int page;

    private int count;

    @NotNull
    private ItemCategory itemCategory;

    @Builder
    public ItemPagingCommand(int page, int count, String itemCategory) {
        this.page = page;
        this.count = count;
        this.itemCategory = itemCategoryValidate(itemCategory);
        this.validateSelf();
    }

    private ItemCategory itemCategoryValidate(String itemCategory){
        try {
            return ItemCategory.valueOf(itemCategory);
        }catch(Exception e){
            throw new ConstraintViolationException("validation fail",new HashSet<>());
        }
    }
}
