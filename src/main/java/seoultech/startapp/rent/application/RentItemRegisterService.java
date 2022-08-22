package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.global.exception.InvalidInputException;
import seoultech.startapp.rent.application.port.in.RentItemRegisterUseCase;
import seoultech.startapp.rent.application.port.in.command.RegisterRentItemCommand;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.application.port.out.SaveRentItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentItem;

import java.util.ArrayList;
import java.util.List;
import seoultech.startapp.rent.domain.RentStatus;
import seoultech.startapp.rent.exception.NotConfirmRentException;

@Service
@RequiredArgsConstructor
public class RentItemRegisterService implements RentItemRegisterUseCase {

  private final SaveRentItemPort saveRentItemPort;
  private final SaveRentPort saveRentPort;
  private final LoadRentPort loadRentPort;
  private final LoadItemPort loadItemPort;

  @Override
  @Transactional
  public void register(RegisterRentItemCommand registerRentItemCommand) {
    Rent rent = loadRentPort.loadById(registerRentItemCommand.getRentId());
    List<Item> items = loadItemPort.loadByIds(registerRentItemCommand.getItemIds());

    if (rent.getAccount() != items.size()) {
      throw new InvalidInputException("item의 수량이 맞지 않습니다.");
    }

    if (rent.getRentStatus()!= RentStatus.CONFIRM) {
      throw new NotConfirmRentException("승인되지 않은 요청입니다.");
    }

    List<RentItem> rentItems = new ArrayList<>();
    for (Item item : items) {
      item.validationRent(rent.getItemCategory());
      item.rent(false);
      rentItems.add(RentItem.builder().item(item).rent(rent).build());
    }

    rent.rent();
    saveRentPort.save(rent);
    saveRentItemPort.saveAll(rentItems);
  }


}
