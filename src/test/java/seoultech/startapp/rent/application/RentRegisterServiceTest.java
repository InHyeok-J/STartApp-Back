package seoultech.startapp.rent.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.rent.application.port.in.command.RentCommand;
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
import static seoultech.startapp.rent.domain.ItemCategory.TABLE;

@ExtendWith(MockitoExtension.class)
class RentRegisterServiceTest {

    @Mock
    private SaveRentPort saveRentPort;

    @Mock
    private LoadItemPort loadItemPort;

    @InjectMocks
    private RentRegisterService rentRegisterService;

    private RentCommand rentCommand;

    private Rent rent;
    private final Long MEMBER_ID = 1L;
    private final int ACCOUNT = 5;
    private final ItemCategory ITEM_CATEGORY = TABLE;
    private final String PURPOSE = "산공과";
    private final LocalDate START_TIME = LocalDate.of(2022, 8, 11);
    private final LocalDate END_TIME = LocalDate.of(2022, 8, 15);

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

        rent = rentCommand.ToDomainRent();

    }

    @Test
    @DisplayName("Rent 등록 요청 성공 빌릴 수 있는 갯수보다 더 적게 요청함")
    void registerRent_ok(){

        ItemCategory itemCategory = rent.getItemCategory();

        given(loadItemPort.countAllCategoryItems(rent.getItemCategory())).willReturn(10L);
        given(saveRentPort.countIncludingEndTIme(rent.getEndTime(),itemCategory)).willReturn(2L);
        given(saveRentPort.countIncludingStartTime(rent.getStartTime(),itemCategory)).willReturn(3L);
        given(loadItemPort.countAvailableFalseCategoryItems(rent.getItemCategory())).willReturn(1L);

        rentRegisterService.registerRent(rentCommand);

        verify(saveRentPort,times(1)).saveRent(refEq(rent));

    }

    @Test
    @DisplayName("Rent 등록 요청 실패 빌릴 수 있는 갯수보다 더 많이 요청함")
    void registerRent_fail(){

        ItemCategory itemCategory = rent.getItemCategory();

        given(loadItemPort.countAllCategoryItems(rent.getItemCategory())).willReturn(10L);
        given(saveRentPort.countIncludingEndTIme(rent.getEndTime(),itemCategory)).willReturn(2L);
        given(saveRentPort.countIncludingStartTime(rent.getStartTime(),itemCategory)).willReturn(6L);
        given(loadItemPort.countAvailableFalseCategoryItems(rent.getItemCategory())).willReturn(1L);

        //캐노피 총 10개 있고 max 값 6개, 고장난 거 1개
        // 10 - 6 - 1 = 3개까지 빌릴 수 있음.
        //근데 5개 요청함
        NotRentItemException notRentItemException = assertThrows(NotRentItemException.class,
                                                                 () -> rentRegisterService.registerRent(rentCommand));

        assertEquals(409,notRentItemException.getErrorType().getStatusCode());

    }



}