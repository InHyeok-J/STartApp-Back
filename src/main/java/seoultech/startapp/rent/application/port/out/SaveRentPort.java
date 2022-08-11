package seoultech.startapp.rent.application.port.out;

import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;

import java.time.LocalDate;

public interface SaveRentPort {

    void saveRent(Rent rent);

    long countIncludingStartTime(LocalDate startTime, ItemCategory itemCategory);

    long countIncludingEndTIme(LocalDate endTime, ItemCategory itemCategory);
}
