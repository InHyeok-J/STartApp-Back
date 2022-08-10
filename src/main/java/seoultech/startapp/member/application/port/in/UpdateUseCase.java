package seoultech.startapp.member.application.port.in;

import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;

public interface UpdateUseCase {

  void update(UpdateMemberCommand command);
}
