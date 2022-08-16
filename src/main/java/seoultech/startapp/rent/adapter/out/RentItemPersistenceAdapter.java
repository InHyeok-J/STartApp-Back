package seoultech.startapp.rent.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.application.port.out.LoadRentItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentItemPort;
import seoultech.startapp.rent.domain.RentItem;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentItemPersistenceAdapter implements SaveRentItemPort, LoadRentItemPort {

    private final JpaRentItemRepository jpaRentItemRepository;

    private final RentItemMapper rentItemMapper;

    @Override
    public void save(List<RentItem> rentItems) {
        List<JpaRentItem> jpaRentItems = rentItems.stream()
                                                  .map(rentItemMapper::mapToJpaRentItem)
                                                  .toList();

//        for(JpaRentItem jpaRentItem : jpaRentItems){
//            jpaRentItem.addJpaRent(jpaRentItem.getJpaRent());
//        }


        jpaRentItemRepository.saveAll(jpaRentItems);
    }

    @Override
    public Boolean existByIds(List<RentItem> rentItems) {
        List<JpaRentItem> jpaRentItems = rentItems.stream().
                                                  map(rentItemMapper::mapToJpaRentItem)
                                                  .toList();
        return Boolean.TRUE;
    }
}
