package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.command.RentCommand;
import seoultech.startapp.rent.application.port.in.RentRegisterUseCase;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;

import static java.lang.Math.max;

@Service
@RequiredArgsConstructor
class RentRegisterService implements RentRegisterUseCase {

    private final SaveRentPort saveRentPort;

    private final LoadItemPort loadItemPort;


    @Override
    @Transactional
    public void registerRent(RentCommand rentCommand) {
        Rent rent = rentCommand.ToDomainRent();

        canRent(rent);

        saveRentPort.saveRent(rent);
    }

    private void canRent(Rent rent){

        ItemCategory itemCategory = rent.getItemCategory();
        long memberRequestAccount = rent.getAccount();

        long countAllCategoryItems = loadItemPort.countAllCategoryItems(itemCategory);

        long countStartTime = saveRentPort.countIncludingStartTime(rent.getStartTime(),itemCategory);
        long countEndTime = saveRentPort.countIncludingEndTIme(rent.getEndTime(),itemCategory);
        long maxCount = max(countStartTime,countEndTime);

        long countFalseItems = loadItemPort.countAvailableFalseCategoryItems(itemCategory);

        long countCurrentCanRentItems = countAllCategoryItems - (maxCount + countFalseItems);

        if(memberRequestAccount > countCurrentCanRentItems){
            throw new NotRentItemException("요청한 갯수만큼 물품을 대여할 수 없습니다.");
        }

    }
}
