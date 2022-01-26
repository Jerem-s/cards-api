package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.CardDto;
import fr.jeremy.cardsapi.dto.mappers.CardMapper;
import fr.jeremy.cardsapi.dto.response.CardResponse;
import fr.jeremy.cardsapi.exceptions.NotFoundException;
import fr.jeremy.cardsapi.models.Card;
import fr.jeremy.cardsapi.repositories.CardRepository;
import fr.jeremy.cardsapi.repositories.projections.CardId;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    public CardResponse getHand() {

        Set<CardDto> cards = new HashSet<>();
        while (cards.size() < cardRepository.count() || cards.size() < 10) {
            Card card = findRandomCard();
            CardDto cardDto = cardMapper.mapToDto(card);
            cards.add(cardDto);
        }
        CardResponse cardResponse = new CardResponse();
        cardResponse.setCards(cards);
        return cardResponse;
    }

    private Card findRandomCard() {
        List<CardId> cardIds = cardRepository.findIdBy();
        int nextInt = new Random().nextInt(cardIds.size());
        CardId cardId = cardIds.get(nextInt);
        return cardRepository.findById(cardId.getId())
                .orElseThrow(() -> new NotFoundException(String.format("No Card found for id: %s", cardId.getId())));
    }

}
