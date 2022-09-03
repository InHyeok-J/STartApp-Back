package seoultech.startapp.member.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.out.LoadMemberShipPort;

@Component
@RequiredArgsConstructor
public class MemberShipPersistenceAdapter implements LoadMemberShipPort {

    private final JpaMemberShipRepository jpaMemberShipRepository;

    @Override
    public boolean existByStudentNo(String studentNo) {
        return jpaMemberShipRepository.existsByStudentNo(studentNo);
    }
}
