package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.mappers.OrderCardColorMapper;
import fr.jeremy.cardsapi.repositories.DeckRepository;
import fr.jeremy.cardsapi.repositories.projections.OrderColorCardsInfo;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final DeckRepository deckRepository;
    private final OrderCardColorMapper orderCardColorMapper;

    public OrderService(DeckRepository deckRepository, OrderCardColorMapper orderCardColorMapper) {
        this.deckRepository = deckRepository;
        this.orderCardColorMapper = orderCardColorMapper;
    }

    public OrderColorResponse findOrderColorByDeckName(String deckName) {
        OrderColorCardsInfo colorCardsByName = deckRepository.findByName(deckName);
        return orderCardColorMapper.map(colorCardsByName);
    }
}
