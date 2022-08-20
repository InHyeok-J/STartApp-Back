package seoultech.startapp.festival.application;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import seoultech.startapp.festival.domain.Booth;

@Getter
@AllArgsConstructor
public class BoothPagingResponse {

  private List<BoothResponse> boothList;
  private int totalPage;

  public static BoothPagingResponse toDto(List<Booth> booths, int totalPage) {
    return new BoothPagingResponse(
        booths.stream().map(BoothResponse::toDto).toList(), totalPage);
  }
}
