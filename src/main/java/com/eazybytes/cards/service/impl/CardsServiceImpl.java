package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardsAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements CardsService {

    private final CardsRepository cardsRepository;
    private final CardsMapper cardsMapper;

    @Override
    public void createCard(String mobileNumber) {
        cardsRepository.findByMobileNumber(mobileNumber)
                .ifPresent(c -> {
            throw new CardsAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        });
        cardsRepository.save(createNewCards(mobileNumber));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        return cardsRepository.findByMobileNumber(mobileNumber)
                .map(cardsMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        cardsMapper.toEntity(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }

    private Cards createNewCards(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}