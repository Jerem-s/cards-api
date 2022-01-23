package fr.jeremy.cardsapi.repository.projections;

import fr.jeremy.cardsapi.model.ColorCard;

import java.util.Set;

public interface DeckInfo {
    String getName();

    OrderCardInfo getOrder();

    interface OrderCardInfo {
        Set<ColorCard> getColorCards();
    }
}
