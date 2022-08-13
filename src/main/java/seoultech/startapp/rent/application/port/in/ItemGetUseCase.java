package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.ItemPagingResponse;

public interface ItemGetUseCase {

    ItemPagingResponse getAllByPaging(int page, int count);

}
