package seoultech.startapp.rent.application.port.out;

import seoultech.startapp.rent.domain.RentItem;

import java.util.List;

public interface LoadRentItemPort {

    Boolean existByIds(List<RentItem> rentItems);
}
