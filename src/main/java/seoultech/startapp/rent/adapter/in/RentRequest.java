package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.rent.application.port.in.RentCommand;
import seoultech.startapp.rent.domain.ItemCategory;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
class RentRequest {

    private ItemCategory itemCategory;

    private String purpose;

    private long amount;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    public RentCommand toRentCommand(){
        return RentCommand.builder()
            .itemCategory(this.itemCategory)
            .amount(this.amount)
            .purpose(this.purpose)
            .startTime(this.startTime)
            .endTime(this.endTime)
            .build();
    }
}
