package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.mappers.OrderColorMapper;
import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.models.OrderColor;
import fr.jeremy.cardsapi.repositories.OrderColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderColorService {

    private final OrderColorRepository orderColorRepository;
    private final OrderColorMapper orderColorMapper;

    public OrderColorService(OrderColorRepository orderColorRepository, OrderColorMapper orderColorMapper) {
        this.orderColorRepository = orderColorRepository;
        this.orderColorMapper = orderColorMapper;
    }

    public OrderColorResponse save(OrderColorRequest orderColorRequest) {
        OrderColor orderColor = orderColorMapper.mapToEntity(orderColorRequest);
        OrderColor save = this.orderColorRepository.save(orderColor);
        return orderColorMapper.mapToDto(save);
    }

    public OrderColorResponse findLast() {
        return this.orderColorRepository.findLastByOrderByCreatedAtAsc().map(orderColorMapper::mapToDto)
                .orElseGet(() -> {
                    OrderColorResponse orderColorResponse = new OrderColorResponse();
                    orderColorResponse.setColors(List.of("SPADES", "DIAMONDS", "HEARTS", "CLUBS"));
                    return orderColorResponse;
                });
    }
}
