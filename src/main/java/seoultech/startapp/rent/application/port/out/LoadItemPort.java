package seoultech.startapp.rent.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.rent.domain.Item;

import java.util.Optional;

public interface LoadItemPort {

    Page<Item> loadAllItemByPaging(PageRequest pageRequest);

    Item loadById(Long itemId);

    Optional<Item> loadByItemNo(String itemNo);
}
