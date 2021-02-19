package com.fx.trade.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fx.trade.service.ValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConvertorUtils {

    private static final Logger logger = LoggerFactory.getLogger(ValidateService.class);

    private ConvertorUtils() {
    }

    public static String toJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e.toString());
        }
        return jsonResult;
    }
}
