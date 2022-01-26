package fr.jeremy.cardsapi.dto.mappers;

import fr.jeremy.cardsapi.dto.CardDto;
import fr.jeremy.cardsapi.models.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper implements DtoMapper<Card, CardDto> {
    @Override
    public CardDto mapToDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setColor(card.getColor().getColor());
        cardDto.setValue(card.getValue().getValue());
        return cardDto;
    }
}
