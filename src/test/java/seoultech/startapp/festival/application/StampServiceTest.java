package seoultech.startapp.festival.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.festival.application.port.in.command.StampCommand;
import seoultech.startapp.festival.application.port.out.LoadStampPort;
import seoultech.startapp.festival.application.port.out.SaveStampPort;
import seoultech.startapp.festival.domain.Stamp;
import seoultech.startapp.festival.exception.AlreadyPrizedException;
import seoultech.startapp.festival.exception.AlreadyStampException;
import seoultech.startapp.festival.exception.RequireStampException;

@ExtendWith(MockitoExtension.class)
class StampServiceTest {

  @Mock
  LoadStampPort loadStampPort;

  @Mock
  SaveStampPort saveStampPort;

  @InjectMocks
  StampService stampService;

  Stamp allFalseStamp;
  Stamp allTrueStamp;
  @BeforeEach
  void setUp(){
    allFalseStamp = Stamp.builder()
        .stampId(1L)
        .memberId(1L)
        .sangsang(false)
        .bungeobang(false)
        .exhibition(false)
        .fleamarket(false)
        .ground(false)
        .isPrized(false)
        .build();
    allTrueStamp = Stamp.builder()
        .stampId(1L)
        .memberId(1L)
        .sangsang(true)
        .bungeobang(true)
        .exhibition(true)
        .fleamarket(true)
        .ground(true)
        .isPrized(true)
        .build();
  }


  @Test
  @DisplayName("스탬프 조회시 없으면 새로운 값 생성후 저장")
  public void getStampLoadAndSave() throws Exception {
    Long memberId = 1L;
    given(loadStampPort.loadByMemberId(memberId)).willReturn(null);
    given(saveStampPort.save(any())).willReturn(allFalseStamp);
    StampResponse result = stampService.getMyStamp(memberId);

    assertEquals(result.getBungeobang(), false);
    assertEquals(result.getExhibition(), false);
    assertEquals(result.getFleamarket(), false);
    assertEquals(result.getSangsang(), false);
    assertEquals(result.getGround(), false);
    assertEquals(result.getIsPrized(),false);
  }

  @Test
  @DisplayName("스탬프 찍기 성공")
  public void stampSuccess() throws Exception {
    StampCommand command = StampCommand.builder()
        .memberId(1L)
        .target("exhibition").build();

    given(loadStampPort.loadByMemberId(command.getMemberId())).willReturn(allFalseStamp);

    stampService.stamp(command);
    // false -> true 로 변경
    assertEquals(allFalseStamp.getExhibition(), true);
  }

  @Test
  @DisplayName("이미 찍혀있는 Stamp라 실패")
  public void stampFail() throws Exception {
    StampCommand command = StampCommand.builder()
        .memberId(1L)
        .target("exhibition").build();
    given(loadStampPort.loadByMemberId(command.getMemberId())).willReturn(allTrueStamp);

    assertThrows(AlreadyStampException.class , ()->
        stampService.stamp(command));
  }

  @Test
  @DisplayName("상품 수여 요청시 다른 STAMP가 안찍혀있으면 실패")
  public void stamp_prized_fail() throws Exception {
    StampCommand command = StampCommand.builder()
        .memberId(1L)
        .target("prized")
        .build();

    given(loadStampPort.loadByMemberId(command.getMemberId())).willReturn(allFalseStamp);

    assertThrows(RequireStampException.class, ()-> stampService.stamp(command));
  }

  @Test
  @DisplayName("상품 수여 요청시 이미 상품을 받았으면 실패")
  public void stamp_prized_fail_alreadyPrized() throws Exception {
    StampCommand command = StampCommand.builder()
        .memberId(1L)
        .target("prized")
        .build();

    given(loadStampPort.loadByMemberId(command.getMemberId())).willReturn(allTrueStamp);

    assertThrows(AlreadyPrizedException.class, ()-> stampService.stamp(command));
  }
}