package seoultech.startapp.rent.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.rent.application.port.in.command.RegisterItemCommand;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.SaveItemPort;
import seoultech.startapp.rent.domain.Item;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ItemRegisterServiceTest {

  @Mock
  private SaveItemPort saveItemPort;

  @InjectMocks
  private ItemRegisterService itemRegisterService;

  @Mock
  private LoadItemPort loadItemPort;

  private RegisterItemCommand registerItemCommand;
  private final String ITEM_NO = "C001";
  private final String ITEM_CATEGORY = "AMP";
  private final Boolean IS_AVAILABLE = TRUE;

  @BeforeEach
  void setUp() {
    registerItemCommand = RegisterItemCommand.builder()
        .itemNo(ITEM_NO)
        .itemCategory(ITEM_CATEGORY)
        .isAvailable(IS_AVAILABLE)
        .build();
  }

//    @Test
//    @DisplayName("loadItemPort의 save가 정상적으로 작동되는 지 확인")
//    void save_ok(){
//        itemRegisterService.registerItem(registerItemCommand);
//
//        given(loadItemPort.existsByItemNo(anyString())).willReturn(FALSE);
//
//        verify(saveItemPort, times(1)).saveItem(any());
//    }

}