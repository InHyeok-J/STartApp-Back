package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.ItemPagingResponse;

public interface ItemGetUseCase {

    ItemPagingResponse getByPaging(int page, int count,String itemCategory);

}
