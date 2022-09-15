package seoultech.startapp.member.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.out.LoadMemberShipPort;
import seoultech.startapp.member.application.port.out.SaveMemberShipPort;

@Component
@RequiredArgsConstructor
public class MemberShipPersistenceAdapter implements LoadMemberShipPort , SaveMemberShipPort {

    private final JpaMemberShipRepository jpaMemberShipRepository;

    @Override
    public boolean existByStudentNo(String studentNo) {
        return jpaMemberShipRepository.existsByStudentNo(studentNo);
    }

    @Override
    public void save(String studentNo) {
        JpaMemberShip jpaMemberShip = new JpaMemberShip(studentNo);
        jpaMemberShipRepository.save(jpaMemberShip);
    }
}
