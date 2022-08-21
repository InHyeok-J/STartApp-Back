package seoultech.startapp.rent.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.rent.application.port.in.command.UpdateRentStatusCommand;
import seoultech.startapp.rent.application.port.out.LoadRentItemPort;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.application.port.out.SaveRentItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentItem;
import seoultech.startapp.rent.domain.RentStatus;

@ExtendWith(MockitoExtension.class)
class RentUpdateServiceTest {

  @Mock
  LoadRentPort loadRentPort;

  @Mock
  SaveRentPort saveRentPort;

  @Mock
  SaveRentItemPort saveRentItemPort;

  @Mock
  LoadRentItemPort loadRentItemPort;

  @InjectMocks
  RentUpdateService rentUpdateService;

  @Test
  @DisplayName("렌트 업데이트 시 Rent의 상태가 바뀐다.")
  public void rentSuccess() throws Exception {
    UpdateRentStatusCommand toConfrimCommand = UpdateRentStatusCommand.builder()
        .rentId(1L)
        .rentStatus("CONFIRM")
        .build();

    Rent savedRent = Rent.builder()
        .rentId(1L)
        .rentStatus(RentStatus.WAIT)
        .account(1)
        .build();

    given(loadRentPort.loadById(1L)).willReturn(savedRent);

    rentUpdateService.updateByStatus(toConfrimCommand);
    assertEquals(savedRent.getRentStatus(), RentStatus.CONFIRM);
  }

  @Test
  @DisplayName("렌트 업데이트 시 DONE 인 경우 item 을 반납 상태로 바꾼다.")
  public void rent_success_and_item_done() throws Exception {
    UpdateRentStatusCommand toDoneCommand = UpdateRentStatusCommand.builder()
        .rentId(1L)
        .rentStatus("DONE")
        .build();

    Rent savedRent = Rent.builder()
        .rentId(1L)
        .rentStatus(RentStatus.RENT)
        .account(1)
        .build();
    List<RentItem> rentItemList = List.of(
        RentItem.builder()
            .rent(savedRent)
            .item(Item.builder().itemId(1L).itemNo("").isAvailable(true).isRentable(false).build())
            .build()
    );

    given(loadRentPort.loadById(1L)).willReturn(savedRent);
    given(loadRentItemPort.loadListByRent(savedRent)).willReturn(rentItemList);

    rentUpdateService.updateByStatus(toDoneCommand);

    assertEquals(savedRent.getRentStatus(),RentStatus.DONE);
    assertEquals(rentItemList.get(0).getItem().getIsRentable(), true);
  }

}