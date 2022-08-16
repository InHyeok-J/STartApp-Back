package seoultech.startapp.rent.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;

public interface LoadRentPort {
    Page<Rent> loadByPaging(PageRequest pageRequest, RentStatus status);
}
