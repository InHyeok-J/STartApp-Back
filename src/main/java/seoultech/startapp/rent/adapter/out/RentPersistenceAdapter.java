package seoultech.startapp.rent.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.application.port.out.CountRentPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class RentPersistenceAdapter implements SaveRentPort, CountRentPort {

    private final JpaRentQueryRepository jpaRentQueryRepository;
    private final JpaRentRepository jpaRentRepository;
    private final RentMapper rentMapper;

    @Override
    public void saveRent(Rent rent) {
        JpaRent jpaRent = rentMapper.mapToJpaRent(rent);
        jpaRentRepository.save(jpaRent);
    }

    @Override
    public long countIncludingStartTime(LocalDate startTime, ItemCategory itemCategory) {
        return jpaRentQueryRepository.countIncludingStartTime(startTime,itemCategory);
    }

    @Override
    public long countIncludingEndTIme(LocalDate endTime, ItemCategory itemCategory) {
        return jpaRentQueryRepository.countIncludingEndTime(endTime,itemCategory);
    }

}
