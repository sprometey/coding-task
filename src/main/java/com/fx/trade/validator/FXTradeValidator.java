package com.fx.trade.validator;

import com.fx.trade.bean.Customer;
import com.fx.trade.bean.ForwardDTO;
import com.fx.trade.bean.OptionDTO;
import com.fx.trade.bean.SpotDTO;
import com.fx.trade.bean.Style;
import com.fx.trade.bean.TradeDTO;
import com.fx.trade.bean.ValidateDTO;
import com.fx.trade.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.fx.trade.bean.Style.AMERICAN;

@Component
public class FXTradeValidator implements TradeValidator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private TimeService timeService;

    @Autowired
    private CurrencyValidator currencyValidator;

    @Override
    public ValidateDTO validate(TradeDTO tradeDTO) {
        ValidateDTO validateDTO = new ValidateDTO();
        List<String> messages = validateDTO.getValidateMessages();
        validateDTO.setDetails(tradeDTO);

        LocalDate tradeDate = tradeDTO.getTradeDate();
        LocalDate valueDate = tradeDTO.getValueDate();

        if (valueDate.isBefore(tradeDate)) {
            messages.add(String.format("Value date '%s' cannot be before trade date '%s'",
                    valueDate.format(DATE_FORMATTER),
                    tradeDate.format(DATE_FORMATTER)));
        }

        if (!timeService.isWorkingDate(valueDate)) {
            messages.add(String.format("Value date '%s' cannot fall on weekend or non-working day for currency",
                    valueDate.format(DATE_FORMATTER)));
        }

        String customer = tradeDTO.getCustomer();

        if (!Customer.isSupported(customer)) {
            messages.add(String.format("Counterparty '%s' isn't supported", customer));
        }

        String ccyPair = tradeDTO.getCcyPair();

        if (!currencyValidator.matchedCurrencyPairToIso(ccyPair)) {
            messages.add("Currencies pair should match ISO 4217 codes");
        }

        return validateDTO;
    }

    /* Requirements:
        Spot, Forward transactions:
        - validate the value date against the product type

        Note sure what does it mean therefore validate by this statement:
        Value dates for most FX trades are "spot", which generally means two business days from the trade date (T+2)
     */

    @Override
    public ValidateDTO validate(SpotDTO spotDTO) {
        ValidateDTO validateDTO = validate((TradeDTO) spotDTO);
        List<String> messages = validateDTO.getValidateMessages();
        validateDTO.setDetails(spotDTO);

        if (!timeService.equalsValueDateAndTradeDatePlus2Days(spotDTO.getValueDate(), spotDTO.getTradeDate())) {
            messages.add(String.format("Value date '%s' should be before 2 business days from the trade date '%s'",
                    spotDTO.getValueDate().format(DATE_FORMATTER),
                    spotDTO.getTradeDate().format(DATE_FORMATTER)));
        }

        return validateDTO;
    }

    /* Requirements:
    Spot, Forward transactions:
    - validate the value date against the product type

    Note sure what does it mean therefore validate by this statement:
    Value dates for most FX trades are "spot", which generally means two business days from the trade date (T+2)
 */
    @Override
    public ValidateDTO validate(ForwardDTO forwardDTO) {
        ValidateDTO validateDTO = validate((TradeDTO) forwardDTO);
        List<String> messages = validateDTO.getValidateMessages();
        validateDTO.setDetails(forwardDTO);

        if (!timeService.equalsValueDateAndTradeDatePlus2Days(forwardDTO.getValueDate(), forwardDTO.getTradeDate())) {
            messages.add(String.format("Value date '%s' should be before 2 business days from the trade date '%s'",
                    forwardDTO.getValueDate().format(DATE_FORMATTER),
                    forwardDTO.getTradeDate().format(DATE_FORMATTER)));
        }

        return validateDTO;
    }

    @Override
    public ValidateDTO validate(OptionDTO optionDTO) {
        ValidateDTO validateDTO = validate((TradeDTO) optionDTO);
        validateDTO.setDetails(optionDTO);
        List<String> messages = validateDTO.getValidateMessages();

        String style = optionDTO.getStyle();

        if (!Style.isSupported(style)) {
            messages.add("The style can be either American or European");
        }

        if (AMERICAN.name().equals(style)) {
            if (!timeService.validateExcerciseStartDate(optionDTO.getTradeDate(), optionDTO.getExcerciseStartDate(), optionDTO.getExpiryDate())) {
                messages.add(
                        String.format("ExcerciseStartDate '%s' has to be after the trade date '%s' but before the expiry date '%s'",
                                optionDTO.getExcerciseStartDate().format(DATE_FORMATTER),
                                optionDTO.getTradeDate().format(DATE_FORMATTER),
                                optionDTO.getExpiryDate().format(DATE_FORMATTER)));
            }
        }

        if (!timeService.validExpiryAndPremiumDates(optionDTO.getExpiryDate(), optionDTO.getPremiumDate(), optionDTO.getDeliveryDate())) {
            messages.add(
                    String.format("Expiry date '%s' and premium date '%s' shall be before delivery date '%s'",
                            optionDTO.getExpiryDate().format(DATE_FORMATTER),
                            optionDTO.getPremiumDate().format(DATE_FORMATTER),
                            optionDTO.getDeliveryDate().format(DATE_FORMATTER)));
        }

        String payCcy = optionDTO.getPayCcy();

        if (!currencyValidator.matchedCurrencyToIso(payCcy)) {
            messages.add("Pay currency should match ISO 4217 codes");
        }

        String premiumCcy = optionDTO.getPremiumCcy();

        if (!currencyValidator.matchedCurrencyToIso(premiumCcy)) {
            messages.add("Premium currency should match ISO 4217 codes");
        }

        return validateDTO;
    }

}