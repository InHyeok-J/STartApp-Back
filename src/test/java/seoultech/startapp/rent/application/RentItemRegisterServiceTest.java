package seoultech.startapp.rent.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.global.exception.InvalidInputException;
import seoultech.startapp.rent.application.port.in.command.RegisterRentItemCommand;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.application.port.out.SaveRentItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;
import seoultech.startapp.rent.exception.NotConfirmRentException;
import seoultech.startapp.rent.exception.NotRentableItemException;

@ExtendWith(MockitoExtension.class)
class RentItemRegisterServiceTest {

  @Mock
  SaveRentItemPort saveRentItemPort;

  @Mock
  SaveRentPort saveRentPort;

  @Mock
  LoadRentPort loadRentPort;

  @Mock
  LoadItemPort loadItemPort;

  @InjectMocks
  RentItemRegisterService rentItemRegisterService;

  RegisterRentItemCommand command;

  @BeforeEach
  void setUp() {
    command = RegisterRentItemCommand.builder()
        .rentId(1L)
        .itemIds(List.of(1L, 2L, 3L))
        .build();
  }


  @Test
  @DisplayName("rent의 수량과 요청한 item의 수량이 안맞는 경우 실패")
  public void rent_fail_account_not_match() throws Exception {
    Rent account3Rent = Rent.builder()
        .rentId(1L)
        .purpose("빌리기")
        .itemCategory(ItemCategory.AMP)
        .account(3)
        .build();
    List<Item> items = mockItemListBySize(4);

    given(loadRentPort.loadById(command.getRentId())).willReturn(account3Rent);
    given(loadItemPort.loadByIds(any())).willReturn(items);

    assertThrows(InvalidInputException.class, () -> rentItemRegisterService.register(command));
  }

  @Test
  @DisplayName("rent의 상태가 Confirm가 아니여서 rent할수 없는 경우 실패")
  public void rent_fail_rentStatus_notConfirm() throws Exception {
    Rent waitedRent = Rent.builder()
        .rentId(1L)
        .purpose("빌리기")
        .account(3)
        .itemCategory(ItemCategory.AMP)
        .rentStatus(RentStatus.WAIT)
        .build();
    List<Item> items = mockItemListBySize(3);

    given(loadRentPort.loadById(command.getRentId())).willReturn(waitedRent);
    given(loadItemPort.loadByIds(any())).willReturn(items);

    assertThrows(NotConfirmRentException.class, () -> rentItemRegisterService.register(command));
  }

  @Test
  @DisplayName("item의 상태가 rent를 할 수 없는 경우 실패")
  public void rent_fail_item_notrentable() throws Exception {
    Rent generalRent = Rent.builder()
        .rentId(1L)
        .purpose("빌리기")
        .account(1)
        .itemCategory(ItemCategory.AMP)
        .rentStatus(RentStatus.CONFIRM)
        .build();
    List<Item> items = List.of(
        Item.builder()
        .itemId(1L).itemNo("ItemNo").isRentable(false).isAvailable(false).itemCategory(ItemCategory.AMP)
        .build());
    given(loadRentPort.loadById(command.getRentId())).willReturn(generalRent);
    given(loadItemPort.loadByIds(any())).willReturn(items);

    assertThrows(NotRentableItemException.class, () -> rentItemRegisterService.register(command));
  }

  @Test
  @DisplayName("가져온 ITEM이 RENT가 필요한 Category가 아닐때 실패 ")
  public void rent_fail_itemCategory_notMatch() throws Exception {
    Rent generalRent = Rent.builder()
        .rentId(1L)
        .purpose("빌리기")
        .account(1)
        .itemCategory(ItemCategory.AMP)
        .rentStatus(RentStatus.CONFIRM)
        .build();

    List<Item> items = List.of(
        Item.builder()
            .itemId(1L).itemNo("ItemNo").isRentable(true).isAvailable(true).itemCategory(ItemCategory.TABLE)
            .build());

    given(loadRentPort.loadById(command.getRentId())).willReturn(generalRent);
    given(loadItemPort.loadByIds(any())).willReturn(items);

    assertThrows(NotRentableItemException.class, () -> rentItemRegisterService.register(command));
  }

  @Test
  @DisplayName("렌트 성공")
  public void rent_success() throws Exception {
    Rent generalRent = Rent.builder()
        .rentId(1L)
        .purpose("빌리기")
        .account(1)
        .itemCategory(ItemCategory.AMP)
        .rentStatus(RentStatus.CONFIRM)
        .build();
    List<Item> items = List.of(
        Item.builder()
            .itemId(1L).itemNo("ItemNo").isRentable(true).isAvailable(true).itemCategory(ItemCategory.AMP)
            .build());

    given(loadRentPort.loadById(command.getRentId())).willReturn(generalRent);
    given(loadItemPort.loadByIds(any())).willReturn(items);

    rentItemRegisterService.register(command);

    // RentStatus RENT상태로 바뀐다.
    // ITEM의 rentable 상태가 false로 바뀐다.
    assertEquals(generalRent.getRentStatus(), RentStatus.RENT);
    assertEquals(items.get(0).getIsRentable(), false);
  }

  private List<Item> mockItemListBySize(int size) {
    List<Item> temp = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      temp.add(mockItemIsOk("itemNo"));
    }
    return temp;
  }

  private Item mockItemIsOk(String itemNo) {
    return Item.builder()
        .itemId(1L)
        .itemNo(itemNo)
        .isAvailable(true)
        .isRentable(true)
        .itemCategory(ItemCategory.AMP)
        .build();
  }

}