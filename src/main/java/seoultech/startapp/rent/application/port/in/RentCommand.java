package seoultech.startapp.rent.application.port.in;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class RentCommand extends SelfValidator<RentCommand> {

    @NotBlank
    private ItemCategory itemCategory;

    @NotNull
    private String purpose;

    @NotBlank
    private long amount;

    @NotBlank
    private LocalDateTime startTime;

    @NotBlank
    private LocalDateTime endTime;

    @Builder
    public RentCommand(ItemCategory itemCategory, String purpose, long amount, LocalDateTime startTime, LocalDateTime endTime) {
        this.itemCategory = itemCategory;
        this.purpose = purpose;
        this.amount = amount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validateSelf();
    }
}
