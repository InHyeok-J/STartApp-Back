package seoultech.startapp.rent.application.port.in.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.validation.ConstraintViolationException;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegisterItemCommandTest {

    private RegisterItemCommand registerItemCommand;

    private final String ITEM_NO = "C001";
    private final String ITEM_CATEGORY = "AMP";
    private final Boolean IS_AVAILABLE = TRUE;

    @BeforeEach
    void setUp(){
        registerItemCommand = RegisterItemCommand.builder()
                                                 .itemNo(ITEM_NO)
                                                 .itemCategory(ITEM_CATEGORY)
                                                 .isAvailable(IS_AVAILABLE)
                                                 .build();

    }

    @Test
    @DisplayName("ItemCommand가 Item으로 변환 성공")
    void itemCommandToDomainItem_Ok(){
        Item item = registerItemCommand.toDomainItem();

        assertThat(item.getItemNo()).isEqualTo(ITEM_NO);
        assertThat(item.getItemCategory()).isEqualTo(ItemCategory.AMP);
        assertThat(item.getIsAvailable()).isEqualTo(TRUE);

    }

    @Test
    @DisplayName("ItemCommand가 Item으로 변환 실패(ItemCatergory에 오타를 적음)")
    void itemCommandToDomainItem_Fail(){

        ConstraintViolationException constraintViolationException = assertThrows(ConstraintViolationException.class,
                                                                                 () -> RegisterItemCommand.builder()
                                                                                                          .itemNo(ITEM_NO)
                                                                                                          .itemCategory("AMMP")
                                                                                                          .isAvailable(IS_AVAILABLE)
                                                                                                          .build());

        Assertions.assertEquals("잘못된 itemCategory를 입력했습니다.",constraintViolationException.getMessage());
    }
}