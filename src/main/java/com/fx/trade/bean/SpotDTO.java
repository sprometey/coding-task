package com.fx.trade.bean;

import com.fx.trade.validator.TradeValidator;

/*
  {
    "customer": "YODA1",
    "ccyPair": "EURUSD",
    "type": "Spot",
    "direction": "SELL",
    "tradeDate": "2020-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2020-08-22",
    "legalEntity": "UBS AG",
    "trader": "Josef Schoenberger"
  }
 */
public class SpotDTO extends TradeDTO {

   public SpotDTO() {
   }

   public SpotDTO(TradeDTOBuilder tradeDTOBuilder) {
      super(tradeDTOBuilder);
   }

   @Override
   public ValidateDTO accept(TradeValidator v) {
      return v.validate(this);
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("SpotDTO{");
      sb.append("customer='").append(getCustomer()).append('\'');
      sb.append(", ccyPair='").append(getCcyPair()).append('\'');
      sb.append(", direction='").append(getDirection()).append('\'');
      sb.append(", tradeDate=").append(getTradeDate());
      sb.append(", amount1=").append(getAmount1());
      sb.append(", amount2=").append(getAmount2());
      sb.append(", rate=").append(getRate());
      sb.append(", valueDate=").append(getValueDate());
      sb.append(", legalEntity='").append(getLegalEntity()).append('\'');
      sb.append(", trader='").append(getTrader()).append('\'');
      sb.append('}');
      return sb.toString();
   }

}
