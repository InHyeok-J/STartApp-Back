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
  private final LocalDate START_TIME = LocalDate.now();
  private final LocalDate END_TIME = LocalDate.now();

  @BeforeEach
  void setUp() {
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
  void rentCommandToDomainRent_ok() {
    Rent rent = registerRentCommand.ToDomainRent();

    assertThat(rent.getRenter().getRenterId()).isEqualTo(MEMBER_ID);
    assertThat(rent.getAccount()).isEqualTo(ACCOUNT);
    assertThat(rent.getItemCategory()).isEqualTo(ItemCategory.TABLE);
    assertThat(rent.getStartTime()).isEqualTo(START_TIME);
    assertThat(rent.getEndTime()).isEqualTo(END_TIME);
  }

  @Test
  @DisplayName("RentCommand가 DomainRent로 변환 실패(itemCategory에 오타입력)")
  void rentCommandToDomainRent_fail() {

    ConstraintViolationException constraintViolationException = assertThrows(
        ConstraintViolationException.class,
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

  @Test
  @DisplayName("startTime, endTime이 오늘 날짜 이전이면 실패")
  public void timeValidation() throws Exception {
    assertThrows(ConstraintViolationException.class, () ->
        RegisterRentCommand.builder()
            .memberId(1L)
            .itemCategory("AMP")
            .purpose("목적")
            .account(1)
            .startTime(LocalDate.now().minusDays(1))
            .endTime(LocalDate.now())
            .build()
    );
  }
}