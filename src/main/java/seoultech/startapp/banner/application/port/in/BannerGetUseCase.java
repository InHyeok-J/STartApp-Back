package seoultech.startapp.banner.application.port.in;

import java.util.List;
import seoultech.startapp.banner.application.BannerPagingResponse;
import seoultech.startapp.banner.application.BannerResponse;

public interface BannerGetUseCase {

  List<BannerResponse> getAllNotDeleted();
  BannerPagingResponse getAll(int page, int count);

}
