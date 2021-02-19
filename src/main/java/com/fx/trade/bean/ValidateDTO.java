package com.fx.trade.bean;

import java.util.ArrayList;
import java.util.List;

public class ValidateDTO {

    private boolean valid;

    private AbstractDTO details;

    private List<String> validateMessages;

    public ValidateDTO() {
        details = null;
        validateMessages = new ArrayList<>();
    }

    public AbstractDTO getDetails() {
        return details;
    }

    public void setDetails(AbstractDTO details) {
        this.details = details;
    }

    public List<String> getValidateMessages() {
        return validateMessages;
    }

    public void setValidateMessages(List<String> validateMessages) {
        this.validateMessages = validateMessages;
    }

    public boolean isValid() {
        return validateMessages.isEmpty();
    }
}
