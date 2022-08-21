package seoultech.startapp.rent.application.port.out;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;

public interface LoadRentPort {
    Page<Rent> loadByPaging(PageRequest pageRequest, RentStatus status);

    Rent loadById(Long rentId);
    Rent loadByIdWithRenter(Long rentId);
    List<Rent> loadListByMemberId(Long memberId);
    List<Rent> loadListByYearMonthCategory(int year, int month, ItemCategory category);
}
