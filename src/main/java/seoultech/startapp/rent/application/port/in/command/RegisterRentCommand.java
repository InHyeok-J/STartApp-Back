package seoultech.startapp.rent.application.port.in.command;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;
import seoultech.startapp.rent.domain.Renter;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;

@Getter
@EqualsAndHashCode
public class RegisterRentCommand extends SelfValidator<RegisterRentCommand> {

    @NotNull
    private Long memberId;

    @NotNull
    private ItemCategory itemCategory;

    @NotBlank
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
        currentTimeValidation(startTime,endTime);
        this.validateSelf();
    }

    private void currentTimeValidation(LocalDate startTime, LocalDate endTime){
        LocalDate currentTime = LocalDate.now();
        if(startTime.isBefore(currentTime) || endTime.isBefore(currentTime)){
            throw new ConstraintViolationException("과거 날짜로 예약할 수 없습니다", new HashSet<>());
        }
    }

    public Rent ToDomainRent(){
        return Rent.builder()
            .renter(Renter.builder().renterId(this.memberId).build())
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
