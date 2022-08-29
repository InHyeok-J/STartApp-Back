package seoultech.startapp.member.adapter.in.dto;

import seoultech.startapp.member.application.port.in.command.FindPasswordCheckCommand;

public record FindPasswordSmsCheckRequest(String studentNo, String code) {

  public FindPasswordCheckCommand toCommand() {
    return new FindPasswordCheckCommand(studentNo, code);
  }

}
