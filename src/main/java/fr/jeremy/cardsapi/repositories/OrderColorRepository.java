package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.OrderColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderColorRepository extends JpaRepository<OrderColor, Long> {

    Optional<OrderColor> findLastByOrderByCreatedAtAsc();

}
