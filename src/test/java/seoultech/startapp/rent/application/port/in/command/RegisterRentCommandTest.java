package seoultech.startapp.rent.application.port.in.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegisterRentCommandTest {


    private RegisterRentCommand registerRentCommand;

    private final Long MEMBER_ID = 1L;

    private final int ACCOUNT = 5;

    private final String ITEM_CATEGORY = "TABLE";

    private final String PURPOSE = "산공과";
    private final LocalDate START_TIME = LocalDate.of(2022, 8, 11);
    private final LocalDate END_TIME = LocalDate.of(2022,8,15);

    @BeforeEach
    void setUp(){
        registerRentCommand = RegisterRentCommand.builder()
                                                 .memberId(MEMBER_ID)
                                                 .account(ACCOUNT)
                                                 .itemCategory(ITEM_CATEGORY)
                                                 .purpose(PURPOSE)
                                                 .startTime(START_TIME)
                                                 .endTime(END_TIME)
                                                 .build();

    }

    @Test
    @DisplayName("RentCommand가 DomainRent로 성공")
    void rentCommandToDomainRent_ok(){
        Rent rent = registerRentCommand.ToDomainRent();

        assertThat(rent.getRenter().getRenterId()).isEqualTo(MEMBER_ID);
        assertThat(rent.getAccount()).isEqualTo(ACCOUNT);
        assertThat(rent.getItemCategory()).isEqualTo(ItemCategory.TABLE);
        assertThat(rent.getStartTime()).isEqualTo(START_TIME);
        assertThat(rent.getEndTime()).isEqualTo(END_TIME);
    }

    @Test
    @DisplayName("RentCommand가 DomainRent로 변환 실패(itemCategory에 오타입력)")
    void rentCommandToDomainRent_fail(){

        ConstraintViolationException constraintViolationException = assertThrows(ConstraintViolationException.class,
                                                                                 () -> RegisterRentCommand.builder()
                                                                                                          .memberId(MEMBER_ID)
                                                                                                          .account(ACCOUNT)
                                                                                                          .itemCategory("TTABLE")
                                                                                                          .purpose(PURPOSE)
                                                                                                          .startTime(START_TIME)
                                                                                                          .endTime(END_TIME)
                                                                                                          .build());

        assertEquals("잘못된 itemCategory를 입력했습니다.", constraintViolationException.getMessage());

    }

}