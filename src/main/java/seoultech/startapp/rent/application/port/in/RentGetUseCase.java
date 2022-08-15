package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.RentPagingResponse;

public interface RentGetUseCase {

    RentPagingResponse getByPaging(int page, int count, String status);
}
