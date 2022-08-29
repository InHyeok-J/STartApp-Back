package seoultech.startapp.festival.adapter.in.dto;

import seoultech.startapp.festival.application.port.in.command.RegisterBoothCommand;

public record RegisterBoothRequest(String name) {

  public RegisterBoothCommand toCommand(){
    return new RegisterBoothCommand(name);
  }
}
