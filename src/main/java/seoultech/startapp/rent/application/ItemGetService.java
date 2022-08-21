package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.ItemGetUseCase;
import seoultech.startapp.rent.application.port.in.command.GetItemCommand;
import seoultech.startapp.rent.application.port.in.command.ItemPagingCommand;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.domain.ItemCategory;

@Service
@RequiredArgsConstructor
class ItemGetService implements ItemGetUseCase {

    private final LoadItemPort loadItemPort;

    @Override
    @Transactional(readOnly = true)
    public ItemPagingResponse getByPaging(ItemPagingCommand itemPagingCommand) {
        int page = itemPagingCommand.getPage();
        int count = itemPagingCommand.getCount();
        ItemCategory itemCategory = itemPagingCommand.getItemCategory();
        Page<ItemResponse> itemResponses = loadItemPort.loadByPaging(PageRequest.of(page, count), itemCategory)
                                             .map(ItemResponse::itemToItemResponse);
        return new ItemPagingResponse(itemResponses.getTotalPages(),itemResponses.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public long getByItemCategory(GetItemCommand getItemCommand) {
        return loadItemPort.loadByCategoryAndAvailableTrue(getItemCommand.getItemCategory());
    }
}
