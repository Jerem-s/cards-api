package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.Card;
import fr.jeremy.cardsapi.repositories.projections.CardId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<CardId> findIdBy();
}
