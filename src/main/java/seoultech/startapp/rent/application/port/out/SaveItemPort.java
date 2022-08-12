package seoultech.startapp.rent.application.port.out;

import seoultech.startapp.rent.domain.Item;

public interface SaveItemPort {

    void saveItem(Item item);
}
