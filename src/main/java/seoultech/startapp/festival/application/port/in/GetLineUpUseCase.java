package seoultech.startapp.festival.application.port.in;

import java.util.List;
import seoultech.startapp.festival.application.LineUpResponse;

public interface GetLineUpUseCase {

  List<LineUpResponse> findAll();
}
