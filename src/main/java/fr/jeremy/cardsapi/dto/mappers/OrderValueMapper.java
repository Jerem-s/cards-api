package fr.jeremy.cardsapi.dto.mappers;

import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.models.OrderValue;
import fr.jeremy.cardsapi.models.ValueCard;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Component
public class OrderValueMapper implements EntityMapper<OrderValueRequest, OrderValue>, DtoMapper<OrderValue, OrderValueResponse> {
    @Override
    public OrderValueResponse mapToDto(OrderValue orderValue) {
        OrderValueResponse orderValueResponse = new OrderValueResponse();
        orderValueResponse.setId(orderValue.getId());
        orderValueResponse
                .setValues(orderValue.getValueCards().stream().map(ValueCard::getValue).collect(Collectors.toList()));

        return orderValueResponse;
    }

    @Override
    public OrderValue mapToEntity(OrderValueRequest orderValueRequest) {
        OrderValue orderValue = new OrderValue();
        orderValue.setCreatedAt(ZonedDateTime.now());
        orderValue.setValueCards(orderValueRequest.getValues().stream().map(ValueCard::new).collect(Collectors.toList()));
        return orderValue;
    }
}
