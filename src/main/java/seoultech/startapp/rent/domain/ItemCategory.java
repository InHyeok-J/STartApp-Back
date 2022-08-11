package seoultech.startapp.rent.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemCategory {
    CANOPY("CANOPY"),
    TABLE("TABLE"),
    AMP("AMP"),
    WIRE("WIRE"),
    CART("CART"),
    CHAIR("CHAIR")
    ;

    private final String value;

    ItemCategory(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ItemCategory from(String value){
        for(ItemCategory category : ItemCategory.values()){
            if(category.getValue().equals(value)){
                return category;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }
}
