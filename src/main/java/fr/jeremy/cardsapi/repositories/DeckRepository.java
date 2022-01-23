package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.Deck;
import fr.jeremy.cardsapi.repositories.projections.DeckInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
    DeckInfo findOrderColorCardsByName(String deckName);
}
