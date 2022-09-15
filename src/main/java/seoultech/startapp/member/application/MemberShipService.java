package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.MemberShipUseCase;
import seoultech.startapp.member.application.port.out.LoadMemberShipPort;
import seoultech.startapp.member.application.port.out.SaveMemberShipPort;
import seoultech.startapp.member.exception.AlreadyExistMemberShipException;

@Service
@RequiredArgsConstructor
public class MemberShipService implements MemberShipUseCase {

  private final LoadMemberShipPort loadMemberShipPort;
  private final SaveMemberShipPort saveMemberShipPort;

  @Override
  @Transactional
  public void addMemberShip(String studentNo) {
    if(loadMemberShipPort.existByStudentNo(studentNo)){
      throw new AlreadyExistMemberShipException("이미 자치회비 납부한 학번입니다.");
    }

    saveMemberShipPort.save(studentNo);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existCheckMemberShip(String studentNo) {
    return loadMemberShipPort.existByStudentNo(studentNo);
  }
}
