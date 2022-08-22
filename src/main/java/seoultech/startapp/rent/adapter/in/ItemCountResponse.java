package seoultech.startapp.rent.adapter.in;

import lombok.Getter;

@Getter
class ItemCountResponse {

    private long count;

    public ItemCountResponse(long count) {
        this.count = count;
    }
}
