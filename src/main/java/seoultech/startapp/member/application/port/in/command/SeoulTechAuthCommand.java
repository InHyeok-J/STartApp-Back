package seoultech.startapp.member.application.port.in.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SeoulTechAuthCommand {

  private final String jsonValue;
  private final String studentNo;
  private final String key;

  @Builder
  public SeoulTechAuthCommand(String jsonValue, String studentNo, String key) {
    this.jsonValue = jsonValue;
    this.studentNo = studentNo;
    this.key = key;
  }
}