package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.RentCommand;
import seoultech.startapp.rent.application.port.in.RentRegisterUseCase;
import seoultech.startapp.rent.application.port.out.SaveRentPort;

@Slf4j
@Service
@RequiredArgsConstructor
class RentRegisterService implements RentRegisterUseCase {

    private final SaveRentPort saveRentPort;

    @Override
    @Transactional
    public void registerRent(RentCommand rentCommand) {
        saveRentPort
    }
}
