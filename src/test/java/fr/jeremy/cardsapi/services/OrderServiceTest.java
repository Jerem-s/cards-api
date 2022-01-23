package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.mappers.OrderCardColorMapper;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.repositories.DeckRepository;
import fr.jeremy.cardsapi.repositories.projections.OrderColorCardsInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        DeckRepository deckRepository = mock(DeckRepository.class);
        OrderCardColorMapper orderCardColorMapper = mock(OrderCardColorMapper.class);
        this.orderService = new OrderService(deckRepository, orderCardColorMapper);

        OrderColorCardsInfo32Cards colorCardsInfo32Cards = new OrderColorCardsInfo32Cards();

        when(deckRepository.findByName("32_CARDS")).thenReturn(colorCardsInfo32Cards);
        when(orderCardColorMapper.map(colorCardsInfo32Cards)).thenReturn(new OrderColorResponseMock());

    }

    @Test
    void should_retrieve_order() {

        //WHEN
        OrderColorResponse orderColorResponse = this.orderService.findOrderColorByDeckName("32_CARDS");

        //THEN
        assertThat(orderColorResponse.getColorsOrder()).containsExactly("SPADES", "DIAMONDS");
    }

    static class OrderColorCardsInfo32Cards implements OrderColorCardsInfo {
        @Override
        public String getName() {
            return "32_CARDS";
        }

        @Override
        public Optional<OrderCardInfo> getOrder() {
            return Optional.of(() -> {
                LinkedHashSet<ColorCard> colorCards = new LinkedHashSet<>();
                colorCards.add(new ColorCard("SPADES"));
                colorCards.add(new ColorCard("DIAMONDS"));
                return colorCards;
            });
        }
    }

    static class OrderColorResponseMock extends OrderColorResponse {
        @Override
        public String getDeckName() {
            return "32_CARDS";
        }

        @Override
        public List<String> getColorsOrder() {
            return List.of("SPADES", "DIAMONDS");
        }
    }

}
