package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import seoultech.startapp.rent.application.port.in.ItemUpdateUseCase;
import seoultech.startapp.rent.application.port.in.command.UpdateItemAvailableCommand;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.SaveItemPort;
import seoultech.startapp.rent.domain.Item;

@Slf4j
@Service
@RequiredArgsConstructor
class ItemUpdateService implements ItemUpdateUseCase {
    private final LoadItemPort loadItemPort;
    private final SaveItemPort saveItemPort;
    @Override
    public void updateByAvailable(UpdateItemAvailableCommand updateItemAvailableCommand) {
        Item item = loadItemPort.loadById(updateItemAvailableCommand.getItemId());
        item.changeAvailable(updateItemAvailableCommand.getAvailable());
        saveItemPort.saveItem(item);
    }
}
