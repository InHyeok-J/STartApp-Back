package seoultech.startapp.member.adapter.in.dto;

import seoultech.startapp.member.application.port.in.command.SmsCheckCommand;

public record SmsCheckRequest (String phoneNo, String code) {

  public SmsCheckCommand toCommand(){
    return new SmsCheckCommand(phoneNo,code);
  }
}
