package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.UpdateUseCase;
import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateService implements UpdateUseCase {

  private final LoadMemberPort loadMemberPort;
  private final SaveMemberPort saveMemberPort;

  @Override
  public void update(UpdateMemberCommand command) {
    Member member = loadMemberPort.loadByMemberId(command.getMemberId());
    member.updateMemberInfo(command);
    saveMemberPort.save(member);
  }
}
