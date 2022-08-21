package seoultech.startapp.rent.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.domain.RentItem;

@Component
@RequiredArgsConstructor
public class RentItemMapper {

    private final ItemMapper itemMapper;

    private final RentMapper rentMapper;

    public RentItem mapToDomainRentItem(JpaRentItem jpaRentItem){
        return RentItem.builder()
            .rent(rentMapper.mapToDomainRent(jpaRentItem.getJpaRent()))
            .item(itemMapper.mapToDomainItem(jpaRentItem.getJpaItem()))
            .createdAt(jpaRentItem.getCreatedAt())
            .updatedAt(jpaRentItem.getUpdatedAt())
            .build();
    }

    public JpaRentItem mapToJpaRentItem(RentItem rentItem){
        return JpaRentItem.builder()
            .jpaRent(rentMapper.mapToJpaRent(rentItem.getRent()))
            .jpaItem(itemMapper.mapToJpaItem(rentItem.getItem()))
            .createdAt(rentItem.getCreatedAt())
            .updatedAt(rentItem.getUpdatedAt())
            .build();
    }


}
