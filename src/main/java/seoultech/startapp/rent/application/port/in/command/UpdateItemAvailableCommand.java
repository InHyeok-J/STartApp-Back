package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateItemAvailableCommand extends SelfValidator<UpdateItemAvailableCommand> {

    @NotNull
    private Long itemId;
    @NotNull
    private Boolean available;

    @Builder
    public UpdateItemAvailableCommand(Long itemId,Boolean available) {
        this.itemId = itemId;
        this.available = available;
        this.validateSelf();
    }
}
