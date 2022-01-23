package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.ColorCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorCardRepository extends JpaRepository<ColorCard, Long> {
}
