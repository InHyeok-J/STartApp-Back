package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.rent.application.port.in.command.RentCommand;
import seoultech.startapp.rent.domain.ItemCategory;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
class RentRequest {

    private Long memberId;

    private ItemCategory itemCategory;

    private int account;

    private String purpose;

    private LocalDate startTime;

    private LocalDate endTime;

    public RentCommand toRentCommand(){
        return RentCommand.builder()
            .memberId(this.memberId)
            .itemCategory(this.itemCategory)
            .purpose(this.purpose)
            .account(this.account)
            .startTime(this.startTime)
            .endTime(this.endTime)
            .build();
    }
}
