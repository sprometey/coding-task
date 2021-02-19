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
    "type": "Spot",
    "direction": "BUY",
    "tradeDate": "2020-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2020-08-15",
    "legalEntity": "UBS AG",
    "trader": "Josef Schoenberger"
  },
 */
public class TradeDTO extends AbstractDTO{

   private String customer;

   private String ccyPair;

   private String direction;

   @JsonFormat (pattern = "yyyy-MM-dd")
   @JsonSerialize(using = LocalDateSerializer.class)
   private LocalDate tradeDate;

   private long amount1;

   private long amount2;

   private float rate;

   @JsonFormat (pattern = "yyyy-MM-dd")
   @JsonSerialize(using = LocalDateSerializer.class)
   private LocalDate valueDate;

   private String legalEntity;

   private String trader;

   public TradeDTO() {
      super();
   }

   public TradeDTO(TradeDTOBuilder tradeDTOBuilder) {
      super();

      this.customer = tradeDTOBuilder.customer;
      this.ccyPair = tradeDTOBuilder.currencyPair;
      this.direction = tradeDTOBuilder.direction;
      this.tradeDate = tradeDTOBuilder.tradeDate;
      this.amount1 = tradeDTOBuilder.amount1;
      this.amount2 = tradeDTOBuilder.amount2;
      this.rate = tradeDTOBuilder.rate;
      this.valueDate = tradeDTOBuilder.valueDate;
      this.legalEntity = tradeDTOBuilder.legalEntity;
      this.trader = tradeDTOBuilder.trader;

   }

   @Override
   public ValidateDTO accept(TradeValidator v) {
      return v.validate(this);
   }

   public String getCustomer() {
      return customer;
   }

   public void setCustomer(String customer) {
      this.customer = customer;
   }

   public String getCcyPair() {
      return ccyPair;
   }

   public void setCcyPair(String ccyPair) {
      this.ccyPair = ccyPair;
   }

   public String getDirection() {
      return direction;
   }

   public void setDirection(String direction) {
      this.direction = direction;
   }

   public LocalDate getTradeDate() {
      return tradeDate;
   }

   public void setTradeDate(LocalDate tradeDate) {
      this.tradeDate = tradeDate;
   }

   public long getAmount1() {
      return amount1;
   }

   public void setAmount1(long amount1) {
      this.amount1 = amount1;
   }

   public long getAmount2() {
      return amount2;
   }

   public void setAmount2(long amount2) {
      this.amount2 = amount2;
   }

   public float getRate() {
      return rate;
   }

   public void setRate(float rate) {
      this.rate = rate;
   }

   public LocalDate getValueDate() {
      return valueDate;
   }

   public void setValueDate(LocalDate valueDate) {
      this.valueDate = valueDate;
   }

   public String getLegalEntity() {
      return legalEntity;
   }

   public void setLegalEntity(String legalEntity) {
      this.legalEntity = legalEntity;
   }

   public String getTrader() {
      return trader;
   }

   public void setTrader(String trader) {
      this.trader = trader;
   }

}
