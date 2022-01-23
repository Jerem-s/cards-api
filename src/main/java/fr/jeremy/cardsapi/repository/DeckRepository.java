package fr.jeremy.cardsapi.repository;

import fr.jeremy.cardsapi.model.Deck;
import fr.jeremy.cardsapi.repository.projections.DeckInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
    DeckInfo findOrderColorCardsByName(String deckName);
}
