package seoultech.startapp.rent.application.port.in.command;

import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Getter
public class GetItemCountCommand extends SelfValidator<GetItemCountCommand> {

    @NotNull
    private ItemCategory itemCategory;

    public GetItemCountCommand(String itemCategory) {
        this.itemCategory = itemCategoryValidate(itemCategory);
        this.validateSelf();
    }

    private ItemCategory itemCategoryValidate(String itemCategory){
        try {
            return ItemCategory.valueOf(itemCategory);
        }catch(Exception e){
            throw new ConstraintViolationException("validation fail", new HashSet<>());
        }
    }
}
