package seoultech.startapp.rent.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.rent.domain.Item;

public interface LoadItemPort {

    Page<Item> loadAllItemByPaging(PageRequest pageRequest);

    Item loadById(Long itemId);

    Boolean existsByItemNo(String itemNo);
}
