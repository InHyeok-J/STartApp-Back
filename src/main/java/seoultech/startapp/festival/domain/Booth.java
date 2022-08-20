package seoultech.startapp.festival.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Booth {

  private Long boothId;
  private String name;
  private int congestion;

  @Builder
  public Booth(Long boothId, String name, int congestion) {
    this.boothId = boothId;
    this.name = name;
    this.congestion = congestion;
  }
}
