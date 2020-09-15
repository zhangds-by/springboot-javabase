package com.zhangds.pool.validate.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CardDTO {

    @NotBlank
    private String cardNum;

    @NotBlank
    private String cardType;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
