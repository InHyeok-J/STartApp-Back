package seoultech.startapp.festival.application.port.out;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.festival.domain.Booth;

public interface LoadBoothPort {

  Booth loadByBoothId(Long id);
  List<Booth> loadAll();
  Page<Booth> loadByPaging(PageRequest pageRequest);
}
