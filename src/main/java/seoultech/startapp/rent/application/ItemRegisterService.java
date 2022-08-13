package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seoultech.startapp.rent.adapter.out.DuplicatedItemNo;
import seoultech.startapp.rent.application.port.in.ItemRegisterUseCase;
import seoultech.startapp.rent.application.port.in.command.RegisterItemCommand;
import seoultech.startapp.rent.application.port.out.LoadItemPort;
import seoultech.startapp.rent.application.port.out.SaveItemPort;
import seoultech.startapp.rent.domain.Item;

@Service
@RequiredArgsConstructor
class ItemRegisterService implements ItemRegisterUseCase {

    private final SaveItemPort saveItemPort;

    private final LoadItemPort loadItemPort;
    @Override
    public void registerItem(RegisterItemCommand registerItemCommand) {
        Item item = registerItemCommand.toDomainItem();
        checkDuplicatedItemNo(item.getItemNo());
        saveItemPort.saveItem(item);
    }

    private void checkDuplicatedItemNo(String itemNo){
        if(loadItemPort.existsByItemNo(itemNo)){
            throw new DuplicatedItemNo("ItemNo이 중복됩니다. 다른 ItemNo을 사용해주세요");
        }
    }

}
