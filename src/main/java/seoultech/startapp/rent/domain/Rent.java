package seoultech.startapp.rent.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Rent {

    private ItemCategory itemCategory;

    private long amount;

    private String purpose;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public Rent(ItemCategory itemCategory, long amount, String purpose, LocalDateTime startTime, LocalDateTime endTime) {
        this.itemCategory = itemCategory;
        this.amount = amount;
        this.purpose = purpose;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
