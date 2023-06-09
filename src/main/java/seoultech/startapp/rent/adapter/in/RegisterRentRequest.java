package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.rent.application.port.in.command.RegisterRentCommand;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
class RegisterRentRequest {

    private String itemCategory;

    private int account;

    private String purpose;

    private LocalDate startTime;

    private LocalDate endTime;

    public RegisterRentCommand toRentCommand(Long memberId){
        return RegisterRentCommand.builder()
                                  .memberId(memberId)
                                  .itemCategory(this.itemCategory)
                                  .purpose(this.purpose)
                                  .account(this.account)
                                  .startTime(this.startTime)
                                  .endTime(this.endTime)
                                  .build();
    }
}
