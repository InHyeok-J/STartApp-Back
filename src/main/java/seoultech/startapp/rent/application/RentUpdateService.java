package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.RentUpdateUseCase;
import seoultech.startapp.rent.application.port.in.command.UpdateRentStatusCommand;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.application.port.out.SaveRentItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;

@Service
@RequiredArgsConstructor
public class RentUpdateService implements RentUpdateUseCase {

    private final LoadRentPort loadRentPort;
    private final SaveRentPort saveRentPort;

    private final SaveRentItemPort saveRentItemPort;

    @Override
    @Transactional
    public void updateByStatus(UpdateRentStatusCommand updateRentStatusCommand) {
        Rent rent = loadRentPort.loadById(updateRentStatusCommand.getRentId());
        update(rent,updateRentStatusCommand.getRentStatus());
    }

    private void update(Rent rent,RentStatus rentStatus){
        if(rentStatus.equals(RentStatus.DONE) || rentStatus.equals(RentStatus.NOT_RETURN)){
//            List<RentItem> rentItems = rent.getRentItems();
//            for(RentItem rentItem : rentItems){
//                rentItem.changeRentItemStatus(rentStatus);
//            }
//            saveRentItemPort.save(rentItems);

        }
        rent.changeRentStatus(rentStatus);
        saveRentPort.save(rent);
    }
}
