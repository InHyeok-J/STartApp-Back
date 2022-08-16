package seoultech.startapp.rent.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.rent.domain.Rent;

import java.time.LocalDate;

@Getter
public class RentResponse {

    private Long rentId;

    private Long memberId;

    private int account;

    private String purpose;

    private String rentStatus;

    private String itemCategory;

    private LocalDate startTime;

    private LocalDate endTime;

    @Builder
    public RentResponse(Long rentId,
                        Long memberId,
                        int account,
                        String purpose,
                        String rentStatus,
                        String itemCategory,
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

    public static RentResponse rentToRentResponse(Rent rent){
        return RentResponse.builder()
            .rentId(rent.getRentId())
            .memberId(rent.getMemberId())
            .account(rent.getAccount())
            .itemCategory(rent.getItemCategory().toString())
            .purpose(rent.getPurpose())
            .rentStatus(rent.getRentStatus().toString())
            .startTime(rent.getStartTime())
            .endTime(rent.getEndTime())
            .build();
    }
}
