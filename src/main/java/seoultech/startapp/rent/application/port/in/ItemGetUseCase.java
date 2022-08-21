package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.ItemPagingResponse;
import seoultech.startapp.rent.application.port.in.command.GetItemCommand;
import seoultech.startapp.rent.application.port.in.command.ItemPagingCommand;

public interface ItemGetUseCase {

    ItemPagingResponse getByPaging(ItemPagingCommand itemPagingCommand);

    long getByItemCategory(GetItemCommand getItemCommand);
}
