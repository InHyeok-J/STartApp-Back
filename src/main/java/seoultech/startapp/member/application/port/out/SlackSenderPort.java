package seoultech.startapp.member.application.port.out;


import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;

public interface SlackSenderPort {

  void sendStudentCard(SlackStudentCardDto dto);
  void sendResponseSlackHook(ResponseSlackHookDto dto);
}
