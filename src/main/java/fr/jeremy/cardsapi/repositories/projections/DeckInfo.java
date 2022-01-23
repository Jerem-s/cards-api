package fr.jeremy.cardsapi.repositories.projections;

import fr.jeremy.cardsapi.models.ColorCard;

import java.util.LinkedHashSet;
import java.util.Set;

public interface DeckInfo {
    String getName();

    default OrderCardInfo getOrder() {
        return LinkedHashSet::new;
    }

    interface OrderCardInfo {
        Set<ColorCard> getColorCards();
    }
}
