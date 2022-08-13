package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.ItemGetUseCase;
import seoultech.startapp.rent.application.port.out.LoadItemPort;

@Service
@RequiredArgsConstructor
class ItemGetService implements ItemGetUseCase {

    private final LoadItemPort loadItemPort;

    @Override
    @Transactional(readOnly = true)
    public ItemPagingResponse getAllByPaging(int page, int count) {
        Page<ItemResponse> itemResponses = loadItemPort.loadAllItemByPaging(PageRequest.of(page, count))
                                             .map(ItemResponse::itemToItemResponse);
        return new ItemPagingResponse(itemResponses.getTotalPages(),itemResponses.getContent());
    }
}
