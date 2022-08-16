package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.RentItemRegisterUseCase;
import seoultech.startapp.rent.application.port.in.command.RegisterRentItemCommand;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.application.port.out.SaveRentItemPort;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentItem;
import seoultech.startapp.rent.domain.RentItemStatus;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentItemRegisterService implements RentItemRegisterUseCase {

    private final SaveRentItemPort saveRentItemPort;
    private final LoadRentPort loadRentPort;
    private final LoadItemPort loadItemPort;

    @Override
    @Transactional
    public void register(RegisterRentItemCommand registerRentItemCommand) {
        Rent rent = loadRentPort.loadById(registerRentItemCommand.getRentId());
        List<Item> items = loadItemPort.loadByIds(registerRentItemCommand.getItemIds());
        RentItemStatus rentItemStatus = registerRentItemCommand.getRentItemStatus();

        List<RentItem> rentItems = new ArrayList<>();
        for(Item item :items){
            rentItems.add(new RentItem(rent,item,rentItemStatus));
        }

        checkAccount(registerRentItemCommand.getRentId(),registerRentItemCommand.getItemIds().size());

        saveRentItemPort.save(rentItems);
    }
    private void checkAccount(Long rentId,int adminAccount){
        Rent rent = loadRentPort.loadById(rentId);
        int memberAccount = rent.getAccount();

        if(memberAccount != adminAccount){
            throw new NotMatchMemberAccountException("member가 요구한 account만큼 물품 개수를 등록해야됩니다.");
        }



    }

}
