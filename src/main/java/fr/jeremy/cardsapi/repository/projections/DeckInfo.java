package fr.jeremy.cardsapi.repository.projections;

import fr.jeremy.cardsapi.model.ColorCard;

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
