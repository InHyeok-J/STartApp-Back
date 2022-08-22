package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.ItemPagingResponse;
import seoultech.startapp.rent.application.port.in.command.GetItemCountCommand;
import seoultech.startapp.rent.application.port.in.command.ItemPagingCommand;

public interface ItemGetUseCase {

    ItemPagingResponse getByPaging(ItemPagingCommand itemPagingCommand);

    long countByItemCategory(GetItemCountCommand getItemCountCommand);
}
