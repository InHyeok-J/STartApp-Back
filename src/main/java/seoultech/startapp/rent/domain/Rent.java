package seoultech.startapp.rent.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Rent {

    private Long rentId;

    private Renter renter;

    private int account;

    private String purpose;

    private RentStatus rentStatus;

    private ItemCategory itemCategory;

    private LocalDate startTime;

    private LocalDate endTime;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    //create_at, update_at

    @Builder
    public Rent(Long rentId,
                Renter renter,
                int account,
                String purpose,
                RentStatus rentStatus,
                ItemCategory itemCategory,
                LocalDate startTime,
                LocalDate endTime,
                LocalDateTime createAt,
                LocalDateTime updateAt) {
        this.rentId = rentId;
        this.renter = renter;
        this.account = account;
        this.purpose = purpose;
        this.rentStatus = rentStatus;
        this.itemCategory = itemCategory;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
    public void changeRentStatus(RentStatus rentStatus){
        this.rentStatus = rentStatus;
    }
}
