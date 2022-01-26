package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.ValueCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValueCardRepository extends JpaRepository<ValueCard, Long> {
    List<ValueCard> findByOrderByRankAsc();
}
