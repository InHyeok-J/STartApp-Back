package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;

@Getter
public class RegisterRentCommand extends SelfValidator<RegisterRentCommand> {

    @NotNull
    private Long memberId;

    @NotNull
    private ItemCategory itemCategory;

    @NotNull
    private String purpose;

    @Min(1)
    @NotNull
    private int account;

    @NotNull
    private LocalDate startTime;

    @NotNull
    private LocalDate endTime;

    @Builder
    public RegisterRentCommand(Long memberId,
                               String itemCategory,
                               String purpose,
                               int account,
                               LocalDate startTime,
                               LocalDate endTime) {
        this.memberId = memberId;
        this.itemCategory = itemCategoryValidate(itemCategory);
        this.purpose = purpose;
        this.account = account;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validateSelf();
    }

    public Rent ToDomainRent(){
        return Rent.builder()
            .memberId(this.memberId)
            .account(this.account)
            .purpose(this.purpose)
            .rentStatus(RentStatus.WAIT)
            .itemCategory(this.itemCategory)
            .startTime(this.startTime)
            .endTime(this.endTime)
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
