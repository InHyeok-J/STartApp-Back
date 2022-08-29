package seoultech.startapp.member.adapter.in.dto;


import seoultech.startapp.member.application.port.in.command.FindPasswordPushCommand;

public record FindPasswordSmsPushRequest(String studentNo) {

  public FindPasswordPushCommand toCommand(){
    return new FindPasswordPushCommand(studentNo);
  }

}
