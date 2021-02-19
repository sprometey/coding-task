package com.fx.trade.bean;

import java.time.LocalDate;

public class TradeDTOBuilder {

   String customer;

   String currencyPair;

   String direction;

   String strategy;

   LocalDate tradeDate;

   long amount1;

   long amount2;

   float rate;

   LocalDate valueDate;

   String legalEntity;

   String trader;

   String style;

   LocalDate deliveryDate;

   LocalDate excerciseStartDate;

   LocalDate expiryDate;

   String payCurency;

   float premium;

   String premiumCurency;

   String premiumType;

   LocalDate premiumDate;

   public TradeDTOBuilder() {
   }

   public SpotDTO buildSpot() {
      return new SpotDTO(this);
   }

   public ForwardDTO buildForward() {
      return new ForwardDTO(this);
   }

   public OptionDTO buildOption() {
      return new OptionDTO(this);
   }

   public TradeDTOBuilder customer(String customer) {
      this.customer = customer;
      return this;
   }

   public TradeDTOBuilder currencyPair(String currencyPair) {
      this.currencyPair = currencyPair;
      return this;
   }

   public TradeDTOBuilder direction(String direction) {
      this.direction = direction;
      return this;
   }

   public TradeDTOBuilder strategy(String strategy) {
      this.strategy = strategy;
      return this;
   }

   public TradeDTOBuilder amount1(Long amount1) {
      this.amount1 = amount1;
      return this;
   }

   public TradeDTOBuilder amount2(Long amount2) {
      this.amount2 = amount2;
      return this;
   }

   public TradeDTOBuilder tradeDate(LocalDate tradeDate) {
      this.tradeDate = tradeDate;
      return this;
   }

   public TradeDTOBuilder valueDate(LocalDate valueDate) {
      this.valueDate = valueDate;
      return this;
   }

   public TradeDTOBuilder rate(float rate) {
      this.rate = rate;
      return this;
   }

   public TradeDTOBuilder legalEntity(String legalEntity) {
      this.legalEntity = legalEntity;
      return this;
   }

   public TradeDTOBuilder trader(String trader) {
      this.trader = trader;
      return this;
   }

   public TradeDTOBuilder style(String style) {
      this.style = style;
      return this;
   }

   public TradeDTOBuilder deliveryDate(LocalDate deliveryDate) {
      this.deliveryDate = deliveryDate;
      return this;
   }

   public TradeDTOBuilder excerciseStartDate(LocalDate excerciseStartDate) {
      this.excerciseStartDate = excerciseStartDate;
      return this;
   }

   public TradeDTOBuilder expiryDate(LocalDate expiryDate) {
      this.expiryDate = expiryDate;
      return this;
   }

   public TradeDTOBuilder payCurency(String payCurency) {
      this.payCurency = payCurency;
      return this;
   }

   public TradeDTOBuilder premium(float premium) {
      this.premium = premium;
      return this;
   }

   public TradeDTOBuilder premiumCurency(String premiumCurency) {
      this.premiumCurency = premiumCurency;
      return this;
   }

   public TradeDTOBuilder premiumType(String premiumType) {
      this.premiumType = premiumType;
      return this;
   }

   public TradeDTOBuilder premiumDate(LocalDate premiumDate) {
      this.premiumDate = premiumDate;
      return this;
   }

}
