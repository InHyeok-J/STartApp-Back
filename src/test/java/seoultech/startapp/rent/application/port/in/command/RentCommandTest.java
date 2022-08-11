package seoultech.startapp.rent.application.port.in.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static seoultech.startapp.rent.domain.ItemCategory.TABLE;

class RentCommandTest {


    private RentCommand rentCommand;

    private final Long MEMBER_ID = 1L;

    private final int ACCOUNT = 5;

    private final ItemCategory ITEM_CATEGORY = TABLE;

    private final String PURPOSE = "산공과";
    private final LocalDate START_TIME = LocalDate.of(2022, 8, 11);
    private final LocalDate END_TIME = LocalDate.of(2022,8,15);

    @BeforeEach
    void setUp(){
        rentCommand = RentCommand.builder()
            .memberId(MEMBER_ID)
            .account(ACCOUNT)
            .itemCategory(ITEM_CATEGORY)
            .purpose(PURPOSE)
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build();

    }

    @Test
    @DisplayName("RentCommand가 DomainRent로 제대로 변환되는 지 확인")
    void rentCommandToDomainRent(){
        Rent rent = rentCommand.ToDomainRent();

        assertThat(rent.getMemberId()).isEqualTo(MEMBER_ID);
        assertThat(rent.getAccount()).isEqualTo(ACCOUNT);
        assertThat(rent.getItemCategory()).isEqualTo(ITEM_CATEGORY);
        assertThat(rent.getStartTime()).isEqualTo(START_TIME);
        assertThat(rent.getEndTime()).isEqualTo(END_TIME);
    }

}