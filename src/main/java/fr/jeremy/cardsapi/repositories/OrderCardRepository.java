package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.OrderCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCardRepository extends JpaRepository<OrderCard, Long> {
}
