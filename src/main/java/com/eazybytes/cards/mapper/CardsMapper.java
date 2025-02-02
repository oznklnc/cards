package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import org.springframework.stereotype.Component;

@Component
public class CardsMapper extends AbstractMapper<Cards, CardsDto> {

    @Override
    public CardsDto toDto(Cards entity) {
        return CardsDto.builder()
                .cardNumber(entity.getCardNumber())
                .cardType(entity.getCardType())
                .mobileNumber(entity.getMobileNumber())
                .totalLimit(entity.getTotalLimit())
                .availableAmount(entity.getAvailableAmount())
                .amountUsed(entity.getAmountUsed())
                .build();
    }

    @Override
    public Cards toEntity(CardsDto dto) {
        Cards cards = new Cards();
        cards.setCardNumber(dto.getCardNumber());
        cards.setCardType(dto.getCardType());
        cards.setMobileNumber(dto.getMobileNumber());
        cards.setTotalLimit(dto.getTotalLimit());
        cards.setAvailableAmount(dto.getAvailableAmount());
        cards.setAmountUsed(dto.getAmountUsed());
        return cards;
    }

    @Override
    public Cards toEntity(CardsDto dto, Cards cards) {
        cards.setCardNumber(dto.getCardNumber());
        cards.setCardType(dto.getCardType());
        cards.setMobileNumber(dto.getMobileNumber());
        cards.setTotalLimit(dto.getTotalLimit());
        cards.setAvailableAmount(dto.getAvailableAmount());
        cards.setAmountUsed(dto.getAmountUsed());
        return cards;
    }

}
