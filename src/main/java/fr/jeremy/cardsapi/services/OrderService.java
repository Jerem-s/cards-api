package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.models.ValueCard;
import fr.jeremy.cardsapi.repositories.ColorCardRepository;
import fr.jeremy.cardsapi.repositories.ValueCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderService {

    private final ColorCardRepository colorCardRepository;
    private final ValueCardRepository valueCardRepository;

    public OrderService(ColorCardRepository colorCardRepository, ValueCardRepository valueCardRepository) {
        this.colorCardRepository = colorCardRepository;
        this.valueCardRepository = valueCardRepository;
    }

    public OrderColorResponse getOrderColors() {
        OrderColorResponse orderColorResponse = new OrderColorResponse();
        orderColorResponse.setColors(this.colorCardRepository.findByOrderByRankAsc());
        return orderColorResponse;
    }

    public OrderValueResponse getOrderValues() {
        OrderValueResponse orderValueResponse = new OrderValueResponse();
        orderValueResponse.setValues(this.valueCardRepository.findByOrderByRankAsc());
        return orderValueResponse;
    }

    public OrderColorResponse saveColors(OrderColorRequest orderColorRequest) {
        List<ColorCard> colorCards = IntStream.range(0, orderColorRequest.getColors().size())
                .mapToObj(i -> new ColorCard(orderColorRequest.getColors().get(i), i)).collect(Collectors.toList());
        List<ColorCard> saved = this.colorCardRepository.saveAll(colorCards);
        return new OrderColorResponse(saved);
    }

    public OrderValueResponse saveValues(OrderValueRequest orderValueRequest) {
        List<ValueCard> colorCards = IntStream.range(0, orderValueRequest.getValues().size())
                .mapToObj(i -> new ValueCard(orderValueRequest.getValues().get(i), i)).collect(Collectors.toList());
        List<ValueCard> saved = this.valueCardRepository.saveAll(colorCards);
        return new OrderValueResponse(saved);
    }
}
