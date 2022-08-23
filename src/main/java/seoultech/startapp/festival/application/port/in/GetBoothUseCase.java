package seoultech.startapp.festival.application.port.in;


import java.util.List;
import seoultech.startapp.festival.application.BoothLineUpGetResponse;
import seoultech.startapp.festival.application.BoothPagingResponse;
import seoultech.startapp.festival.application.BoothResponse;

public interface GetBoothUseCase {

  BoothLineUpGetResponse getAll();
  BoothPagingResponse getBoothListPaging(int page ,int count);

}
