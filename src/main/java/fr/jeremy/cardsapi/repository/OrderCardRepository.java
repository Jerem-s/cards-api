package fr.jeremy.cardsapi.repository;

import fr.jeremy.cardsapi.model.OrderCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCardRepository extends JpaRepository<OrderCard, Long> {
}
