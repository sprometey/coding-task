package com.fx.trade.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fx.trade.converter.LocalDateSerializer;
import com.fx.trade.validator.TradeValidator;

import java.time.LocalDate;

/*
  {
    "customer": "YODA1",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "EUROPEAN",
    "direction": "BUY",
    "strategy": "CALL",
    "tradeDate": "2020-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2020-08-22",
    "expiryDate": "2020-08-19",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2020-08-12",
    "legalEntity": "UBS AG",
    "trader": "Josef Schoenberger"
  },

 */
public class OptionDTO extends TradeDTO {

   private String strategy;

   private String style;

   @JsonFormat (pattern = "yyyy-MM-dd")
   @JsonSerialize(using = LocalDateSerializer.class)
   private LocalDate deliveryDate;

   @JsonFormat (pattern = "yyyy-MM-dd")
   @JsonSerialize(using = LocalDateSerializer.class)
   private LocalDate excerciseStartDate;

   @JsonFormat (pattern = "yyyy-MM-dd")
   @JsonSerialize(using = LocalDateSerializer.class)
   private LocalDate expiryDate;

   private String payCcy;

   private float premium;

   private String premiumCcy;

   private String premiumType;

   @JsonFormat (pattern = "yyyy-MM-dd")
   @JsonSerialize(using = LocalDateSerializer.class)
   private LocalDate premiumDate;

   public OptionDTO() {
   }

   public OptionDTO(TradeDTOBuilder tradeDTOBuilder) {
      super(tradeDTOBuilder);
      this.strategy = tradeDTOBuilder.strategy;
      this.style = tradeDTOBuilder.style;
      this.deliveryDate = tradeDTOBuilder.deliveryDate;
      this.excerciseStartDate = tradeDTOBuilder.excerciseStartDate;
      this.expiryDate = tradeDTOBuilder.expiryDate;
      this.payCcy = tradeDTOBuilder.payCurency;
      this.premium = tradeDTOBuilder.premium;
      this.premiumCcy = tradeDTOBuilder.premiumCurency;
      this.premiumType = tradeDTOBuilder.premiumType;
      this.premiumDate = tradeDTOBuilder.premiumDate;
   }

   @Override
   public ValidateDTO accept(TradeValidator v) {
      return v.validate(this);
   }

   public String getStrategy() {
      return strategy;
   }

   public void setStrategy(String strategy) {
      this.strategy = strategy;
   }

   public String getStyle() {
      return style;
   }

   public void setStyle(String style) {
      this.style = style;
   }

   public LocalDate getDeliveryDate() {
      return deliveryDate;
   }

   public void setDeliveryDate(LocalDate deliveryDate) {
      this.deliveryDate = deliveryDate;
   }

   public LocalDate getExcerciseStartDate() {
      return excerciseStartDate;
   }

   public void setExcerciseStartDate(LocalDate excerciseStartDate) {
      this.excerciseStartDate = excerciseStartDate;
   }

   public LocalDate getExpiryDate() {
      return expiryDate;
   }

   public void setExpiryDate(LocalDate expiryDate) {
      this.expiryDate = expiryDate;
   }

   public String getPayCcy() {
      return payCcy;
   }

   public void setPayCcy(String payCcy) {
      this.payCcy = payCcy;
   }

   public float getPremium() {
      return premium;
   }

   public void setPremium(float premium) {
      this.premium = premium;
   }

   public String getPremiumCcy() {
      return premiumCcy;
   }

   public void setPremiumCcy(String premiumCcy) {
      this.premiumCcy = premiumCcy;
   }

   public String getPremiumType() {
      return premiumType;
   }

   public void setPremiumType(String premiumType) {
      this.premiumType = premiumType;
   }

   public LocalDate getPremiumDate() {
      return premiumDate;
   }

   public void setPremiumDate(LocalDate premiumDate) {
      this.premiumDate = premiumDate;
   }

   /*
   Suggest that delivery date for Option is value date
    */
   //TODO assumption
   @Override
   public LocalDate getValueDate() {
      return deliveryDate;
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("OptionDTO{");
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
      sb.append(", strategy='").append(strategy).append('\'');
      sb.append(", style='").append(style).append('\'');
      sb.append(", deliveryDate=").append(deliveryDate);
      sb.append(", excerciseStartDate=").append(excerciseStartDate);
      sb.append(", expiryDate=").append(expiryDate);
      sb.append(", payCcy='").append(payCcy).append('\'');
      sb.append(", premium=").append(premium);
      sb.append(", premiumCcy='").append(premiumCcy).append('\'');
      sb.append(", premiumType='").append(premiumType).append('\'');
      sb.append(", premiumDate=").append(premiumDate);
      sb.append('}');
      return sb.toString();
   }
}
