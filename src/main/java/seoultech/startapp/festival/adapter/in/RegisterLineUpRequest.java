package seoultech.startapp.festival.adapter.in;

import java.time.LocalDate;
import java.time.LocalDateTime;
import seoultech.startapp.festival.application.port.in.command.RegisterLineUpCommand;

public record RegisterLineUpRequest(String title, LocalDate lineUpDay, LocalDateTime lineUpTime) {

  public RegisterLineUpCommand toCommand(){
    return RegisterLineUpCommand.builder()
        .title(title)
        .lineUpDay(lineUpDay)
        .lineUpTime(lineUpTime)
        .build();
  }
}
