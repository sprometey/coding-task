package com.fx.trade.bean;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fx.trade.validator.TradeValidator;

//@JsonDeserialize (using = AbstractDTODeserializer.class)

@JsonTypeInfo (
      use = JsonTypeInfo.Id.NAME,
      include = JsonTypeInfo.As.PROPERTY,
      property = "type")
@JsonSubTypes ( {
                    @JsonSubTypes.Type (value = SpotDTO.class, name = "Spot"),
                    @JsonSubTypes.Type (value = ForwardDTO.class, name = "Forward"),
                    @JsonSubTypes.Type (value = OptionDTO.class, name = "VanillaOption")
              })
public abstract class AbstractDTO {

    private String type;

   public AbstractDTO() {
   }

   public abstract ValidateDTO accept(TradeValidator v);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
