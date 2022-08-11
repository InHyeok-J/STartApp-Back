package seoultech.startapp.rent.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.rent.application.port.in.command.ItemCommand;
import seoultech.startapp.rent.application.port.out.SaveItemPort;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

import static java.lang.Boolean.TRUE;
import static org.mockito.BDDMockito.refEq;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.times;
import static seoultech.startapp.rent.domain.ItemCategory.AMP;

@ExtendWith(MockitoExtension.class)
class ItemRegisterServiceTest {

    @Mock
    private SaveItemPort saveItemPort;

    @InjectMocks
    private ItemRegisterService itemRegisterService;

    private ItemCommand itemCommand;
    private final String ITEM_NO = "C001";
    private final ItemCategory ITEM_CATEGORY = AMP;
    private final Boolean IS_AVAILABLE = TRUE;

    @BeforeEach
    void setUp(){
        itemCommand = ItemCommand.builder()
                                 .itemNo(ITEM_NO)
                                 .itemCategory(ITEM_CATEGORY)
                                 .isAvailable(IS_AVAILABLE)
                                 .build();
    }

    @Test
    @DisplayName("loadItemPort의 save가 정상적으로 작동되는 지 확인")
    void save_ok(){
        itemRegisterService.registerItem(itemCommand);
        Item item = itemCommand.toDomainItem();
        verify(saveItemPort, times(1)).saveItem(refEq(item));
    }

}