package seoultech.startapp.rent.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.rent.application.port.in.command.RentCalendarCommand;
import seoultech.startapp.rent.application.port.in.command.RentPagingCommand;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;
import seoultech.startapp.rent.domain.Renter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentGetServiceTest {

    @Mock
    LoadRentPort loadRentPort;
    @InjectMocks
    RentGetService rentGetService;
    private List<Rent> rents = new ArrayList<>();
    private Page<Rent> rentPage;
    private PageRequest pageRequest = PageRequest.of(0,10);
    private final RentStatus RENT_STATUS = RentStatus.WAIT;

    private List<Rent> calendarRent = new ArrayList<>();


    @BeforeEach
    void setUp(){
        for(int i = 1;i<=50;i++){
            Rent rent = Rent.builder()
                             .rentId(Long.valueOf(i))
                             .rentStatus(RentStatus.WAIT)
                             .itemCategory(ItemCategory.TABLE)
                             .account(i)
                             .renter(Renter.builder().renterId(Long.valueOf(i)).build())
                             .startTime(LocalDate.now().plusDays(i))
                             .endTime(LocalDate.now().plusDays(i + 1))
                             .purpose("목적" + i)
                             .build();

            rents.add(rent);
        }

        rentPage = new PageImpl<>(rents,pageRequest,50);

        for(int i = 1;i<=5;i++){
            Rent rent = Rent.builder()
                            .rentId(Long.valueOf(i))
                            .rentStatus(checkRentStatus(i))
                            .itemCategory(ItemCategory.CANOPY)
                            .account(i)
                            .renter(Renter.builder().renterId(Long.valueOf(i)).build())
                            .startTime(LocalDate.of(2022,8,1).plusDays(i))
                            .endTime(LocalDate.of(2022,8,4).plusDays(i + 1))
                            .purpose("목적" + i)
                            .build();

            calendarRent.add(rent);
        }

    }

    @Test
    @DisplayName("페이지네이션 토탈페이지 확인")
    void getRentPagingByStatus_ok(){
        when(loadRentPort.loadByPaging(pageRequest, RENT_STATUS))
            .thenReturn(rentPage);

        RentPagingCommand rentPagingCommand = RentPagingCommand.builder()
                                                   .page(0)
                                                   .count(10)
                                                   .rentStatus("WAIT")
                                                   .build();

        RentPagingResponse pagingResponse = rentGetService.getByPaging(rentPagingCommand);

        assertThat(pagingResponse.getTotalPage()).isEqualTo(5);

    }

    @Test
    @DisplayName("상시사업 예약 페이지에서 현황 보여주기")
    void getCalendar_ok(){

        LocalDate startTime = LocalDate.of(2022,8,1);
        LocalDate endTime = LocalDate.of(2022,9,1);

        when(loadRentPort.loadListByYearMonthCategory(startTime,endTime,ItemCategory.CANOPY))
            .thenReturn(calendarRent);

        List<RentResponse> calendar = rentGetService.getCalendar(RentCalendarCommand.builder()
                                                                                  .year(2022)
                                                                                  .month(8)
                                                                                  .itemCategory("CANOPY")
                                                                                  .build());

        assertThat(calendar.size()).isEqualTo(5);
    }


    private RentStatus checkRentStatus(int num){
        switch (num){
            case 1:
                return RentStatus.RENT;
            case 2:
                return RentStatus.WAIT;
            case 3:
                return RentStatus.NOT_RETURN;
            default:
                return RentStatus.CONFIRM;
        }
    }
}