package seoultech.startapp.rent.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;
import seoultech.startapp.rent.domain.Renter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentResponse {

    private Long rentId;

    private Renter renter;
    private int account;

    private String purpose;

    private RentStatus rentStatus;

    private ItemCategory itemCategory;

    private LocalDate startTime;

    private LocalDate endTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public RentResponse(Long rentId,
                        Renter renter,
                        int account,
                        String purpose,
                        RentStatus rentStatus,
                        ItemCategory itemCategory,
                        LocalDate startTime,
                        LocalDate endTime,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt) {
        this.rentId = rentId;
        this.renter = renter;
        this.account = account;
        this.purpose = purpose;
        this.rentStatus = rentStatus;
        this.itemCategory = itemCategory;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }




    public static RentResponse rentToRentResponse(Rent rent){
        return RentResponse.builder()
            .rentId(rent.getRentId())
            .renter(rent.getRenter())
            .account(rent.getAccount())
            .itemCategory(rent.getItemCategory())
            .purpose(rent.getPurpose())
            .rentStatus(rent.getRentStatus())
            .startTime(rent.getStartTime())
            .endTime(rent.getEndTime())
            .build();
    }

    public static RentResponse toListResponse(Rent rent){
        return RentResponse.builder()
            .rentId(rent.getRentId())
            .account(rent.getAccount())
            .rentStatus(rent.getRentStatus())
            .itemCategory(rent.getItemCategory())
            .startTime(rent.getStartTime())
            .endTime(rent.getEndTime())
            .build();
    }
}
