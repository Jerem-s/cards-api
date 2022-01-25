package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.OrderValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderValueRepository extends JpaRepository<OrderValue, Long> {
    Optional<OrderValue> findFirstByOrderByCreatedAtDesc();
}
