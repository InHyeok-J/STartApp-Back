package seoultech.startapp.banner.application.port.out;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.banner.domain.Banner;

public interface LoadBannerPort {

  List<Banner> loadAllByNotDeleted();
  Page<Banner> loadAllByPaging(PageRequest pageRequest);
}
