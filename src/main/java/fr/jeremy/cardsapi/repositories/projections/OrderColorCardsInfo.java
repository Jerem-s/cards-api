package fr.jeremy.cardsapi.repositories.projections;

import fr.jeremy.cardsapi.models.ColorCard;

import java.util.Optional;
import java.util.Set;

public interface OrderColorCardsInfo {
    String getName();

    Optional<OrderCardInfo> getOrderColor();

    interface OrderCardInfo {
        Set<ColorCard> getColorCards();
    }
}
