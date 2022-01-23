package fr.jeremy.cardsapi.mappers;

import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.repositories.projections.OrderColorCardsInfo;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Component
public class OrderCardColorMapper implements Mapper<OrderColorCardsInfo, OrderColorResponse> {
    @Override
    public OrderColorResponse map(OrderColorCardsInfo orderColorCardsInfo) {
        OrderColorResponse orderColorResponse = new OrderColorResponse();
        orderColorResponse.setDeckName(orderColorCardsInfo.getName());
        orderColorResponse.setColorsOrder(
                orderColorCardsInfo
                        .getOrder()
                        .map(OrderColorCardsInfo.OrderCardInfo::getColorCards)
                        .orElse(new LinkedHashSet<>())
                        .stream().map(ColorCard::getColor)
                        .collect(Collectors.toList()));
        return orderColorResponse;
    }
}
