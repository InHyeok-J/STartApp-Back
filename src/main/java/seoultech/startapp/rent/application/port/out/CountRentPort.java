package seoultech.startapp.rent.application.port.out;

import seoultech.startapp.rent.domain.ItemCategory;

import java.time.LocalDate;

public interface CountRentPort {

    long countIncludingStartTime(LocalDate startTime, ItemCategory itemCategory);

    long countIncludingEndTIme(LocalDate endTime, ItemCategory itemCategory);
}
