package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seoultech.startapp.rent.application.port.in.ItemRegisterUseCase;
import seoultech.startapp.rent.application.port.in.command.ItemCommand;
import seoultech.startapp.rent.application.port.out.SaveItemPort;
import seoultech.startapp.rent.domain.Item;

@Service
@RequiredArgsConstructor
class ItemRegisterService implements ItemRegisterUseCase {

    private final SaveItemPort saveItemPort;
    @Override
    public void registerItem(ItemCommand itemCommand) {
        Item item = itemCommand.toDomainItem();
        saveItemPort.saveItem(item);
    }
}
