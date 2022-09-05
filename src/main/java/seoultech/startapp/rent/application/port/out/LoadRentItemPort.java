package seoultech.startapp.rent.application.port.out;

import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentItem;

import java.util.List;

public interface LoadRentItemPort {

    List<RentItem> loadListByRent(Rent rent);
}
