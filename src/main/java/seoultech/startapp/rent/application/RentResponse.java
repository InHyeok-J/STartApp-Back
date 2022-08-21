package seoultech.startapp.rent.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentItem;
import seoultech.startapp.rent.domain.RentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentResponse {

    private Long rentId;

    private RenterResponse renter;
    private int account;

    private String purpose;

    private RentStatus rentStatus;

    private ItemCategory itemCategory;

    private LocalDate startTime;

    private LocalDate endTime;

    private List<ItemResponse> rentItemList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public RentResponse(Long rentId,
        RenterResponse renterResponse,
                        int account,
                        String purpose,
                        RentStatus rentStatus,
                        ItemCategory itemCategory,
                        LocalDate startTime,
                        LocalDate endTime,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt,
                        List<ItemResponse> rentItemList) {
        this.rentId = rentId;
        this.renter = renterResponse;
        this.account = account;
        this.purpose = purpose;
        this.rentStatus = rentStatus;
        this.itemCategory = itemCategory;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.rentItemList = rentItemList;
    }

    public static RentResponse toMyRentList(Rent rent){
        return RentResponse.builder()
            .rentId(rent.getRentId())
            .account(rent.getAccount())
            .purpose(rent.getPurpose())
            .rentStatus(rent.getRentStatus())
            .itemCategory(rent.getItemCategory())
            .startTime(rent.getStartTime())
            .endTime(rent.getEndTime())
            .createdAt(rent.getCreateAt())
            .updatedAt(rent.getUpdateAt())
            .build();
    }


    public static RentResponse toDetailResponse(Rent rent, List<RentItem> rentItemList){
        return RentResponse.builder()
            .rentId(rent.getRentId())
            .renterResponse(RenterResponse.toDto(rent.getRenter()))
            .account(rent.getAccount())
            .itemCategory(rent.getItemCategory())
            .purpose(rent.getPurpose())
            .rentStatus(rent.getRentStatus())
            .startTime(rent.getStartTime())
            .endTime(rent.getEndTime())
            .createdAt(rent.getCreateAt())
            .updatedAt(rent.getUpdateAt())
            .rentItemList(rentItemList.stream().map(rentItem -> ItemResponse.itemToItemResponse(rentItem.getItem())).toList())
            .build();
    }

    public static RentResponse toListResponse(Rent rent){
        return RentResponse.builder()
            .rentId(rent.getRentId())
            .account(rent.getAccount())
            .renterResponse(RenterResponse.toDto(rent.getRenter()))
            .rentStatus(rent.getRentStatus())
            .itemCategory(rent.getItemCategory())
            .startTime(rent.getStartTime())
            .endTime(rent.getEndTime())
            .createdAt(rent.getCreateAt())
            .updatedAt(rent.getUpdateAt())
            .build();
    }
}
