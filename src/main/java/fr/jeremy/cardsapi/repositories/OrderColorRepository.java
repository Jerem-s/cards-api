package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.OrderColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderColorRepository extends JpaRepository<OrderColor, Long> {
}
