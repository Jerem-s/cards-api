package fr.jeremy.cardsapi.dto.mappers;

import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.models.OrderColor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Component
public class OrderColorMapper
        implements EntityMapper<OrderColorRequest, OrderColor>, DtoMapper<OrderColor, OrderColorResponse> {
    @Override
    public OrderColorResponse mapToDto(OrderColor orderColor) {
        OrderColorResponse orderColorResponse = new OrderColorResponse();
        orderColorResponse.setId(orderColor.getId());
        orderColorResponse
                .setColors(orderColor.getColorCards().stream().map(ColorCard::getColor).collect(Collectors.toList()));
        return orderColorResponse;
    }

    @Override
    public OrderColor mapToEntity(OrderColorRequest orderColorRequest) {
        OrderColor orderColor = new OrderColor();
        orderColor.setCreatedAt(ZonedDateTime.now());
        orderColor
                .setColorCards(orderColorRequest.getColors().stream().map(ColorCard::new).collect(Collectors.toList()));
        return orderColor;
    }
}
