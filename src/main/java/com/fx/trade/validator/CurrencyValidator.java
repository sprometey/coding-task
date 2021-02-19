package com.fx.trade.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Currency;
import java.util.Set;

@Component
public class CurrencyValidator {

    public static final int CURRENCY_PAIR_LENGTH = 6;

    public boolean matchedCurrencyPairToIso(String currencyPair) {

        if (StringUtils.isEmpty(currencyPair) || currencyPair.length() != CURRENCY_PAIR_LENGTH) {
            return false;
        }

        String currency1 = currencyPair.substring(0, 3);
        String currency2 = currencyPair.substring(3);

        Set<Currency> currencies = Currency.getAvailableCurrencies();
        try {
            return currencies.contains(Currency.getInstance(currency1))
                    && currencies.contains(Currency.getInstance(currency2));
        } catch (IllegalArgumentException e) {
            return false;
        }

    }
    public boolean matchedCurrencyToIso(String currency) {

        Set<Currency> currencies = Currency.getAvailableCurrencies();
        try {
            return currencies.contains(Currency.getInstance(currency));
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

}
