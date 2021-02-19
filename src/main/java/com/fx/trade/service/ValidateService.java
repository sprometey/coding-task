package com.fx.trade.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fx.trade.bean.AbstractDTO;
import com.fx.trade.bean.ValidateDTO;
import com.fx.trade.validator.FXTradeValidator;
import com.fx.trade.validator.ValidateProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateService {

    private static final Logger logger = LoggerFactory.getLogger(ValidateService.class);

    @Autowired
    private FXTradeValidator fxTradeValidator;

    @Lookup
    public ValidateProcessor getValidateProcessor() {
        return null;
    }

    public ValidateDTO validate(AbstractDTO abstractDTO) throws JsonProcessingException {

        ValidateProcessor validateProcessor = getValidateProcessor();
        ValidateDTO validateDTO = validateProcessor.accept(abstractDTO, fxTradeValidator);
        logValidateDTO(validateDTO);

        validateDTO.setDetails(null);

        return validateDTO;

    }

    public List<ValidateDTO> validate(List<AbstractDTO> abstractDTOs) throws JsonProcessingException {

        ValidateProcessor validateProcessor = getValidateProcessor();
        validateProcessor.add(abstractDTOs);
        List<ValidateDTO> validateDTOs = validateProcessor.accept(fxTradeValidator);
        validateDTOs.forEach(this::logValidateDTO);

        return validateDTOs;
    }

    private void logValidateDTO(ValidateDTO validateDTO) {
        logger.info(validateDTO.getDetails().toString());
        validateDTO.getValidateMessages().forEach(logger::error);
    }


}
