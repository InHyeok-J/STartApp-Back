package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import seoultech.startapp.rent.application.port.in.command.RegisterItemCommand;

@Getter
class RegisterItemRequest {

    private String itemCategory;

    private String itemNo;

    private Boolean isAvailable;

    public RegisterItemCommand toItemCommand(){
        return RegisterItemCommand.builder()
                                  .itemCategory(this.itemCategory)
                                  .itemNo(this.itemNo)
                                  .isAvailable(this.isAvailable)
                                  .build();
    }
}
