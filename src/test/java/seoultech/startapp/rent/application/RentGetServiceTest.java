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
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;

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


    @BeforeEach
    void setUp(){
        for(int i = 1;i<=50;i++){
            Rent rent = Rent.builder()
                             .rentId(Long.valueOf(i))
                             .rentStatus(RentStatus.WAIT)
                             .itemCategory(ItemCategory.TABLE)
                             .account(i)
                             .memberId(Long.valueOf(i))
                             .startTime(LocalDate.now().plusDays(i))
                             .endTime(LocalDate.now().plusDays(i + 1))
                             .purpose("목적" + i)
                             .build();

            rents.add(rent);
        }

        rentPage = new PageImpl<>(rents,pageRequest,50);

    }

    @Test
    @DisplayName("페이지네이션 토탈페이지 확인")
    void getRentPagingByStatus_ok(){
        when(loadRentPort.loadByPaging(pageRequest, RENT_STATUS))
            .thenReturn(rentPage);

        RentPagingResponse pagingResponse = rentGetService.getByPaging(0, 10, "WAIT");

        assertThat(pagingResponse.getTotalPage()).isEqualTo(5);

    }

}