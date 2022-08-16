package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.rent.application.port.in.command.UpdateRentStatusCommand;

@Getter
@NoArgsConstructor
class UpdateRentStatusRequest {

    private Long rentId;
    private String rentStatus;


    public UpdateRentStatusCommand ToUpdateRentCommand(){
        return UpdateRentStatusCommand.builder()
                                      .rentStatus(rentStatus)
                                      .rentId(rentId)
                                      .build();
    }
}
