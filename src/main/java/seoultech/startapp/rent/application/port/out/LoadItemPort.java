package seoultech.startapp.rent.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.rent.domain.Item;
import seoultech.startapp.rent.domain.ItemCategory;

import java.util.List;

public interface LoadItemPort {

    Page<Item> loadByPaging(PageRequest pageRequest, ItemCategory itemCategory);

    Item loadById(Long itemId);

    List<Item> loadByIds(List<Long> itemIds);
    Boolean existsByItemNo(String itemNo);
}
