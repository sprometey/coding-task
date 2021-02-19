package com.fx.trade.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class TimeService {

    public boolean validateExcerciseStartDate(LocalDate tradeDate, LocalDate excerciseStartDate, LocalDate expiryDate) {
        return excerciseStartDate.isAfter(tradeDate) && excerciseStartDate.isBefore(expiryDate);
    }

    public boolean equalsValueDateAndTradeDatePlus2Days(LocalDate valueDate, LocalDate tradeDate) {
        return valueDate.isEqual(tradeDate.plusDays(2));
    }

    public boolean isWorkingDate(LocalDate date) {
        return !(isWeekend(date) || isNonWorkingDate(date));
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return DayOfWeek.SATURDAY.equals(dayOfWeek) || DayOfWeek.SUNDAY.equals(dayOfWeek);
    }

    //TODO implements non-working calendar depend on some condition (country, trading schedule )
    private boolean isNonWorkingDate(LocalDate date) {
        return false;
    }

    public boolean validExpiryAndPremiumDates(LocalDate expiryDate, LocalDate premiumDate, LocalDate deliveryDate) {
        return expiryDate.isBefore(deliveryDate) && premiumDate.isBefore(deliveryDate);
    }
}
