package seoultech.startapp.rent.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;

import java.time.LocalDate;
import java.util.List;

public interface LoadRentPort {
    Page<Rent> loadByPaging(PageRequest pageRequest, RentStatus status);

    Rent loadById(Long rentId);
    Rent loadByIdWithRenter(Long rentId);
    List<Rent> loadListByMemberId(Long memberId);
    List<Rent> loadListByYearMonthCategory(LocalDate startTime, LocalDate endTime, ItemCategory category);
}
