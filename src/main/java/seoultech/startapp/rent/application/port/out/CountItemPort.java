package seoultech.startapp.rent.application.port.out;

import seoultech.startapp.rent.domain.ItemCategory;

public interface CountItemPort {

    long countByCategory(ItemCategory itemCategory);

    long countNotAvailableByCategory(ItemCategory itemCategory);
}
