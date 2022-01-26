package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.ColorCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorCardRepository extends JpaRepository<ColorCard, Long> {

    List<ColorCard> findByOrderByRankAsc();

}
