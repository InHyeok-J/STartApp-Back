package seoultech.startapp.rent.application.port.in.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static seoultech.startapp.rent.domain.ItemCategory.AMP;

class ItemCommandTest {

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
    @DisplayName("ItemCommand가 Item으로 제대로 변환되는 지 확인")
    void itemCommantToDomainItem(){
        Item item = itemCommand.toDomainItem();

        assertThat(item.getItemNo()).isEqualTo(ITEM_NO);
        assertThat(item.getItemCategory()).isEqualTo(ITEM_CATEGORY);
        assertThat(item.getIsAvailable()).isEqualTo(TRUE);


    }
}