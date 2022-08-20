package seoultech.startapp.festival.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.festival.domain.Booth;

@Getter
public class BoothResponse {

  private Long boothId;
  private String name;
  private int congestion;

  @Builder
  public BoothResponse(Long boothId, String name, int congestion) {
    this.boothId = boothId;
    this.name = name;
    this.congestion = congestion;
  }

  public static BoothResponse toDto(Booth booth){
    return BoothResponse.builder()
        .boothId(booth.getBoothId())
        .name(booth.getName())
        .congestion(booth.getCongestion())
        .build();
  }
}
