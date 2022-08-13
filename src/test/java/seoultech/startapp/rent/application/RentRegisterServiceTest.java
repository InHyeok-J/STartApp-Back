package seoultech.startapp.rent.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.rent.application.port.in.command.RegisterRentCommand;
import seoultech.startapp.rent.application.port.out.CountItemPort;
import seoultech.startapp.rent.application.port.out.CountRentPort;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RentRegisterServiceTest {

    @Mock
    private SaveRentPort saveRentPort;

    @Mock
    private LoadItemPort loadItemPort;

    @Mock
    private CountRentPort countRentPort;

    @Mock
    private CountItemPort countItemPort;

    @InjectMocks
    private RentRegisterService rentRegisterService;

    private RegisterRentCommand registerRentCommand;

    private Rent rent;
    private final Long MEMBER_ID = 1L;
    private final int ACCOUNT = 5;
    private final String ITEM_CATEGORY = "TABLE";
    private final String PURPOSE = "산공과";
    private final LocalDate START_TIME = LocalDate.of(2022, 8, 11);
    private final LocalDate END_TIME = LocalDate.of(2022, 8, 15);

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

        rent = registerRentCommand.ToDomainRent();

    }

    @Test
    @DisplayName("Rent 등록 요청 성공 빌릴 수 있는 갯수보다 더 적게 요청함")
    void registerRent_ok(){

        ItemCategory itemCategory = rent.getItemCategory();

        given(countItemPort.countByCategory(rent.getItemCategory())).willReturn(10L);
        given(countItemPort.countNotAvailableByCategory(rent.getItemCategory())).willReturn(1L);
        given(countRentPort.countIncludingEndTIme(rent.getEndTime(),itemCategory)).willReturn(2L);
        given(countRentPort.countIncludingStartTime(rent.getStartTime(),itemCategory)).willReturn(3L);

        rentRegisterService.registerRent(registerRentCommand);

        verify(saveRentPort,times(1)).saveRent(refEq(rent));

    }

    @Test
    @DisplayName("Rent 등록 요청 실패 빌릴 수 있는 갯수보다 더 많이 요청함")
    void registerRent_fail(){

        ItemCategory itemCategory = rent.getItemCategory();

        given(countItemPort.countByCategory(rent.getItemCategory())).willReturn(10L);
        given(countItemPort.countNotAvailableByCategory(rent.getItemCategory())).willReturn(1L);
        given(countRentPort.countIncludingEndTIme(rent.getEndTime(),itemCategory)).willReturn(2L);
        given(countRentPort.countIncludingStartTime(rent.getStartTime(),itemCategory)).willReturn(6L);

        //캐노피 총 10개 있고 max 값 6개, 고장난 거 1개
        // 10 - 6 - 1 = 3개까지 빌릴 수 있음.
        //근데 5개 요청함
        ExceedAvailableItem exceedNumberOfCurrentAvailableItem = assertThrows(ExceedAvailableItem.class,
                                                                                             () -> rentRegisterService.registerRent(
                                                                                                 registerRentCommand));

        assertEquals(409, exceedNumberOfCurrentAvailableItem.getErrorType().getStatusCode());

    }



}