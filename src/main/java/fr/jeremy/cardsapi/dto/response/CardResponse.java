package fr.jeremy.cardsapi.dto.response;

import fr.jeremy.cardsapi.dto.CardDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CardResponse {
    Set<CardDto> cards;
}
