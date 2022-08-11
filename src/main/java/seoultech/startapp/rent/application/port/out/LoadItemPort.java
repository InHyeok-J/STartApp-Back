package seoultech.startapp.rent.application.port.out;

import seoultech.startapp.rent.domain.ItemCategory;

public interface LoadItemPort {

    long countAllCategoryItems(ItemCategory itemCategory);

    long countAvailableFalseCategoryItems(ItemCategory itemCategory);
}
