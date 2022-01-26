package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.mappers.OrderValueMapper;
import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.models.OrderValue;
import fr.jeremy.cardsapi.repositories.OrderValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderValueService {

    private final OrderValueRepository orderValueRepository;
    private final OrderValueMapper orderValueMapper;

    public OrderValueService(OrderValueRepository orderValueRepository, OrderValueMapper orderValueMapper) {
        this.orderValueRepository = orderValueRepository;
        this.orderValueMapper = orderValueMapper;
    }

    public OrderValueResponse save(OrderValueRequest orderValueRequest) {
        OrderValue orderValue = orderValueMapper.mapToEntity(orderValueRequest);
        OrderValue save = this.orderValueRepository.save(orderValue);
        return orderValueMapper.mapToDto(save);
    }


    public OrderValueResponse findLast() {
        return this.orderValueRepository.findFirstByOrderByCreatedAtDesc().map(orderValueMapper::mapToDto)
                .orElseGet(() -> {
                    OrderValueResponse orderValueResponse = new OrderValueResponse();
                    orderValueResponse.setValues(List.of("ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"));
                    return orderValueResponse;
                });
    }
}
