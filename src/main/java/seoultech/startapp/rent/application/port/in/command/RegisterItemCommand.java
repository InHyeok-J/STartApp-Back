package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Slf4j
@Getter
public class RegisterItemCommand extends SelfValidator<RegisterItemCommand> {

    @NotNull
    private ItemCategory itemCategory;

    @NotNull
    private String itemNo;

    @NotNull
    private Boolean isAvailable;

    @Builder
    public RegisterItemCommand(String itemCategory, String itemNo, Boolean isAvailable) {
        this.itemCategory = itemCategoryValidate(itemCategory);
        this.itemNo = itemNo;
        this.isAvailable = isAvailable;
        this.validateSelf();
    }

    public Item toDomainItem(){
        return Item.builder()
            .itemNo(itemNo)
            .itemCategory(itemCategory)
            .isAvailable(isAvailable)
            .isRentable(Boolean.TRUE)
            .build();
    }

    private ItemCategory itemCategoryValidate(String itemCategory){
        try{
            return ItemCategory.valueOf(itemCategory);
        }catch (Exception e){
            throw new ConstraintViolationException("잘못된 itemCategory를 입력했습니다.", new HashSet<>());
        }
    }
}
