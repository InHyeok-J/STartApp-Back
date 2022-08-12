package seoultech.startapp.rent.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Rent {

    private Long rentId;

    private Long memberId;

    private int account;

    private String purpose;

    private RentStatus rentStatus;

    private ItemCategory itemCategory;

    private LocalDate startTime;

    private LocalDate endTime;

    @Builder
    public Rent(Long rentId,
                Long memberId,
                int account,
                String purpose,
                RentStatus rentStatus,
                ItemCategory itemCategory,
                LocalDate startTime,
                LocalDate endTime) {
        this.rentId = rentId;
        this.memberId = memberId;
        this.account = account;
        this.purpose = purpose;
        this.rentStatus = rentStatus;
        this.itemCategory = itemCategory;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
