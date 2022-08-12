package seoultech.startapp.banner.adapter.in;

import seoultech.startapp.banner.application.port.in.command.UpdatePriorityCommand;

public record UpdatePriorityRequest(int priority) {

  public UpdatePriorityCommand toUpdatePriority(Long id){
    return UpdatePriorityCommand.builder()
        .bannerId(id)
        .priority(priority)
        .build();
  }
}
