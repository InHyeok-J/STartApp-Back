package seoultech.startapp.member.application.port.in;

import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import seoultech.startapp.member.application.AllToken;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;

public interface RegisterUseCase {

  void register(RegisterCommand command);
  void studentCardSlackHook(BlockActionPayload payload);
}
