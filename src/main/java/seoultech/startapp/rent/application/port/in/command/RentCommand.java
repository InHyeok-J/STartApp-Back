package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class RentCommand extends SelfValidator<RentCommand> {

    @NotNull
    private Long memberId;

    @NotNull
    private ItemCategory itemCategory;

    @NotNull
    private String purpose;

    @NotNull
    private int account;

    @NotNull
    private LocalDate startTime;

    @NotNull
    private LocalDate endTime;

    @Builder
    public RentCommand(Long memberId,
                       ItemCategory itemCategory,
                       String purpose,
                       int account,
                       LocalDate startTime,
                       LocalDate endTime) {
        this.memberId = memberId;
        this.itemCategory = itemCategory;
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
}
