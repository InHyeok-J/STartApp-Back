package seoultech.startapp.festival.adapter.in.dto;

import seoultech.startapp.festival.application.port.in.command.UpdateCongestionBoothCommand;

public record UpdateCongestionBootRequest(int congestion) {

  public UpdateCongestionBoothCommand toCommand(Long boothId){
    return new UpdateCongestionBoothCommand(congestion, boothId);
  }
}
