package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.model.ColorCard;
import fr.jeremy.cardsapi.repository.DeckRepository;
import fr.jeremy.cardsapi.repository.projections.DeckInfo;
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
