package seoultech.startapp.banner.application.port.in;

import seoultech.startapp.banner.application.port.in.command.UpdatePriorityCommand;

public interface UpdatePriorityUserCase {

  void updatePriority(UpdatePriorityCommand command);
}
