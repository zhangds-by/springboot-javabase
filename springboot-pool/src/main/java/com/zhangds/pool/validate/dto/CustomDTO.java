package com.zhangds.pool.validate.dto;

import com.zhangds.pool.entities.member.User;
import com.zhangds.pool.validate.anno.CaseMode;
import com.zhangds.pool.validate.anno.CheckCase;

import javax.validation.Valid;
import java.util.List;

public class CustomDTO {

    @CheckCase(value = CaseMode.UPPER)
    private String name;

    @Valid
    UserDTO userDTO;

    @Valid
    List<CardDTO> cardDTOList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<CardDTO> getCardDTOList() {
        return cardDTOList;
    }

    public void setCardDTOList(List<CardDTO> cardDTOList) {
        this.cardDTOList = cardDTOList;
    }
}
