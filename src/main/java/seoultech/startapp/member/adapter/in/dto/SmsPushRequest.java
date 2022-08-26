package seoultech.startapp.member.adapter.in.dto;

import seoultech.startapp.member.application.port.in.command.SmsPushCommand;

public record SmsPushRequest(String phoneNo) {

  public SmsPushCommand toCommand(){
    return new SmsPushCommand(phoneNo);
  }
}
