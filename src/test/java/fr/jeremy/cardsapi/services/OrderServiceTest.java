package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.models.ValueCard;
import fr.jeremy.cardsapi.repositories.ColorCardRepository;
import fr.jeremy.cardsapi.repositories.ValueCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    private OrderService orderService;
    private ColorCardRepository colorCardRepository;
    private ValueCardRepository valueCardRepository;

    @BeforeEach
    void setUp() {
        this.colorCardRepository = mock(ColorCardRepository.class);
        this.valueCardRepository = mock(ValueCardRepository.class);
        this.orderService = new OrderService(colorCardRepository, valueCardRepository);

    }

    @Test
    void should_call__color_repository_save() {
        // GIVEN
        OrderColorRequest orderColorRequest = new OrderColorRequest();
        orderColorRequest.setColors(List.of("SPADES", "DIAMONDS", "HEARTS", "CLUBS"));
        List<ColorCard> colors = List.of(new ColorCard("SPADES", 1), new ColorCard("DIAMONDS", 2),
                new ColorCard("HEARTS", 3), new ColorCard("CLUBS", 4));
        when(colorCardRepository.saveAll(colors)).thenReturn(colors);

        // WHEN
        OrderColorResponse result = orderService.saveColors(orderColorRequest);

        // THEN
        verify(colorCardRepository).saveAll(colors);

        assertThat(result.getColors()).containsExactly(new ColorCard("SPADES", 1), new ColorCard("DIAMONDS", 2),
                new ColorCard("HEARTS", 3), new ColorCard("CLUBS", 4));
    }

    @Test
    void should_call_value_repository_save_all() {
        // GIVEN
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        orderValueRequest.setValues(List.of("ACE", "TWO", "THREE", "FOUR"));
        List<ValueCard> values = List.of(new ValueCard("ACE", 1), new ValueCard("TWO", 2),
                new ValueCard("THREE", 3), new ValueCard("FOUR", 4));
        when(valueCardRepository.saveAll(values)).thenReturn(values);

        // WHEN
        OrderValueResponse result = orderService.saveValues(orderValueRequest);

        // THEN
        verify(valueCardRepository).saveAll(values);

        assertThat(result.getValues()).containsExactly(new ValueCard("ACE", 1), new ValueCard("TWO", 2),
                new ValueCard("THREE", 3), new ValueCard("FOUR", 4));
    }

}
