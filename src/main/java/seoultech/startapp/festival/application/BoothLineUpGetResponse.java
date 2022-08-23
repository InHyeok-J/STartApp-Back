package seoultech.startapp.festival.application;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoothLineUpGetResponse {

  private List<BoothResponse> boothList;
  private List<LineUpResponse> lineUpList;

  @Builder
  public BoothLineUpGetResponse(
      List<BoothResponse> boothList,
      List<LineUpResponse> lineUpList) {
    this.boothList = boothList;
    this.lineUpList = lineUpList;
  }
}
