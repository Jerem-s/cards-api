package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.response.CardResponse;
import fr.jeremy.cardsapi.services.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public CardResponse getHand() {
        return cardService.getHand();
    }
}
