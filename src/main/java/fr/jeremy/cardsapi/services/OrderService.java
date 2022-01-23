package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.repositories.DeckRepository;
import fr.jeremy.cardsapi.repositories.projections.DeckInfo;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderService {

    private final DeckRepository deckRepository;

    public OrderService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public Set<ColorCard> findOrderColorByDeckName(String deckName) {
        DeckInfo deckInfo = deckRepository.findOrderColorCardsByName(deckName);
        return deckInfo.getOrder().getColorCards();
    }
}
