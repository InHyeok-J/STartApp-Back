package seoultech.startapp.rent.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

public interface LoadItemPort {

    Page<Item> loadByPaging(PageRequest pageRequest, ItemCategory itemCategory);

    Item loadById(Long itemId);

    Boolean existsByItemNo(String itemNo);
}
