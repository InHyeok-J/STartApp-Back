package seoultech.startapp.festival.adapter.in;

import seoultech.startapp.festival.application.port.in.command.RegisterBoothCommand;

public record RegisterBoothRequest(String name) {

  RegisterBoothCommand toCommand(){
    return new RegisterBoothCommand(name);
  }
}
