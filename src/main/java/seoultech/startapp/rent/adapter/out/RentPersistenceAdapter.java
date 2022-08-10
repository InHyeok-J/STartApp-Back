package seoultech.startapp.rent.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.Rent;

@Component
@RequiredArgsConstructor
public class RentPersistenceAdapter implements SaveRentPort {

    private final JpaRentQueryRepository jpaRentQueryRepository;
    private final JpaRentRepository jpaRentRepository;
    private final RentMapper rentMapper;

    @Override
    public void saveRent(Rent rent) {

    }
}
