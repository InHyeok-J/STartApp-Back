package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.command.RegisterRentCommand;
import seoultech.startapp.rent.application.port.in.RentRegisterUseCase;
import seoultech.startapp.rent.application.port.out.CountRentPort;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;

import java.time.LocalDate;

import static java.lang.Math.max;

@Service
@RequiredArgsConstructor
class RentRegisterService implements RentRegisterUseCase {

    private final SaveRentPort saveRentPort;

    private final LoadItemPort loadItemPort;

    private final CountRentPort countRentPort;


    @Override
    @Transactional
    public void registerRent(RegisterRentCommand registerRentCommand) {
        Rent rent = registerRentCommand.ToDomainRent();
        ItemCategory itemCategory = rent.getItemCategory();

        long memberRequestAccount = rent.getAccount();

        long alreadyRentedAccount = getAlreadyRentedAccount(rent.getStartTime(),rent.getEndTime(),itemCategory);
        long trueAvailableItemCount = getAvailableItemCount(itemCategory);

        checkCanRent(memberRequestAccount,trueAvailableItemCount,alreadyRentedAccount);

        saveRentPort.saveRent(rent);
    }

    private long getAlreadyRentedAccount(LocalDate startTime, LocalDate endTime,ItemCategory itemCategory) {

        long includedStartTimeCount = countRentPort.countIncludingStartTime(startTime,itemCategory);
        long includedEndTimeCount = countRentPort.countIncludingEndTIme(endTime,itemCategory);

        return max(includedStartTimeCount,includedEndTimeCount);
    }

    private long getAvailableItemCount(ItemCategory itemCategory){

        long itemCategoryCount = loadItemPort.countByCategory(itemCategory);
        long notAvailableCategory = loadItemPort.countNotAvailableByCategory(itemCategory);

        return itemCategoryCount - notAvailableCategory;
    }

    private void checkCanRent(long memberRequestAccount,long trueAvailableItemCount,long maxNotRentAccount){

        long currentCanRentItemCount = trueAvailableItemCount - maxNotRentAccount;

        if(memberRequestAccount > currentCanRentItemCount){
            throw new ExceedAvailableItem("요청한 갯수만큼 물품을 대여할 수 없습니다.");
        }
    }
}
