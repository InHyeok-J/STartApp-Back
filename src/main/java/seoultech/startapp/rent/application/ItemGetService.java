package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.ItemGetUseCase;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.domain.ItemCategory;

@Service
@RequiredArgsConstructor
class ItemGetService implements ItemGetUseCase {

    private final LoadItemPort loadItemPort;

    @Override
    @Transactional(readOnly = true)
    public ItemPagingResponse getByPaging(int page, int count,String itemCategory) {
        Page<ItemResponse> itemResponses = loadItemPort.loadByPaging(PageRequest.of(page, count), ItemCategory.valueOf(itemCategory))
                                             .map(ItemResponse::itemToItemResponse);
        return new ItemPagingResponse(itemResponses.getTotalPages(),itemResponses.getContent());
    }
}
