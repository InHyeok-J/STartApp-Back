package seoultech.startapp.rent.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.application.port.out.LoadRentItemPort;
import seoultech.startapp.rent.application.port.out.SaveRentItemPort;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentItem;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentItemPersistenceAdapter implements SaveRentItemPort, LoadRentItemPort {

    private final JpaRentItemRepository jpaRentItemRepository;
    private final RentMapper rentMapper;
    private final RentItemMapper rentItemMapper;

    @Override
    public void saveAll(List<RentItem> rentItems) {
        List<JpaRentItem> jpaRentItems = rentItems.stream()
                                                  .map(rentItemMapper::mapToJpaRentItem)
                                                  .toList();
        jpaRentItemRepository.saveAll(jpaRentItems);
    }

    @Override
    public Boolean existByIds(List<RentItem> rentItems) {
        List<JpaRentItem> jpaRentItems = rentItems.stream().
                                                  map(rentItemMapper::mapToJpaRentItem)
                                                  .toList();
        return Boolean.TRUE;
    }

    @Override
    public List<RentItem> loadListByRent(Rent rent) {
        List<JpaRentItem> jpaRentItems = jpaRentItemRepository.findAllByJpaRent(
            rentMapper.mapToJpaRent(rent));
        return jpaRentItems.stream().map(rentItemMapper::mapToDomainRentItem).toList();
    }
}
