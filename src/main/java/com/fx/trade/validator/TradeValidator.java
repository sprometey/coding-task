package com.fx.trade.validator;

import com.fx.trade.bean.ForwardDTO;
import com.fx.trade.bean.OptionDTO;
import com.fx.trade.bean.SpotDTO;
import com.fx.trade.bean.TradeDTO;
import com.fx.trade.bean.ValidateDTO;

public interface TradeValidator {

    ValidateDTO validate(TradeDTO tradeDTO);

    ValidateDTO validate(SpotDTO spotDTO);

    ValidateDTO validate(ForwardDTO forwardDTO);

    ValidateDTO validate(OptionDTO optionDTO);
}
