package seoultech.startapp.rent.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.RentUpdateUseCase;
import seoultech.startapp.rent.application.port.in.command.UpdateRentStatusCommand;
import seoultech.startapp.rent.application.port.out.LoadRentItemPort;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.application.port.out.SaveRentItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentItem;
import seoultech.startapp.rent.domain.RentStatus;

@Service
@RequiredArgsConstructor
public class RentUpdateService implements RentUpdateUseCase {

    private final LoadRentPort loadRentPort;
    private final SaveRentPort saveRentPort;

    private final SaveRentItemPort saveRentItemPort;
    private final LoadRentItemPort loadRentItemPort;

    @Override
    @Transactional
    public void updateByStatus(UpdateRentStatusCommand updateRentStatusCommand) {
        Rent rent = loadRentPort.loadById(updateRentStatusCommand.getRentId());
        rent.changeRentStatus(updateRentStatusCommand.getRentStatus());

        saveRentPort.save(rent);
        if(rent.getRentStatus() == RentStatus.DONE){
            List<RentItem> rentItemList = loadRentItemPort.loadListByRent(rent);
            for(RentItem item : rentItemList){
                item.getItem().rent(true); // item을 이용 가능하게 변환
            }
            saveRentItemPort.saveAll(rentItemList);
        }
    }

}
