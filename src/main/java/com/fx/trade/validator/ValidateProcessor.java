package com.fx.trade.validator;

import com.fx.trade.bean.AbstractDTO;
import com.fx.trade.bean.ValidateDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class ValidateProcessor {

    private List<AbstractDTO> products = new ArrayList<>();

    public ValidateDTO accept(AbstractDTO abstractDTO, TradeValidator v) {
        return abstractDTO.accept(v);
    }

    public List<ValidateDTO> accept(TradeValidator v) {
        List<ValidateDTO> validateDTOs = new ArrayList<>();
        products.forEach(product -> validateDTOs.add(product.accept(v)));
        return validateDTOs;
    }

    public void add(List<AbstractDTO> dtos) {
        products.addAll(dtos);
    }
}