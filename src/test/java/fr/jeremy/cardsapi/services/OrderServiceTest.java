package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.repositories.DeckRepository;
import fr.jeremy.cardsapi.repositories.projections.DeckInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService orderService;
    private DeckRepository deckRepository;

    @BeforeEach
    void setUp() {
        this.deckRepository = mock(DeckRepository.class);
        this.orderService = new OrderService(deckRepository);

        when(deckRepository.findOrderColorCardsByName("32_CARDS")).thenReturn(new DeckInfo32Cards());

    }

    @Test
    void should_retrieve_order() {

        //WHEN
        Set<ColorCard> cardsOrder = this.orderService.findOrderColorByDeckName("32_CARDS");

        //THEN
        assertThat(cardsOrder).containsExactly(new ColorCard("SPADES"), new ColorCard("DIAMONDS"));
    }

    @Test
    void should_retrieve_order_even_if_order_is_not_provided() {
        //GIVEN
        when(deckRepository.findOrderColorCardsByName("32_CARDS")).thenReturn(new DeckInfo32Cards_NoOrder());

        //WHEN
        Set<ColorCard> cardsOrder = this.orderService.findOrderColorByDeckName("32_CARDS");

        //THEN
        assertThat(cardsOrder).isEmpty();
    }

    static class DeckInfo32Cards implements DeckInfo {
        @Override
        public String getName() {
            return "32_CARDS";
        }

        @Override
        public OrderCardInfo getOrder() {
            return () -> {
                LinkedHashSet<ColorCard> colorCards = new LinkedHashSet<>();
                colorCards.add(new ColorCard("SPADES"));
                colorCards.add(new ColorCard("DIAMONDS"));
                return colorCards;
            };
        }
    }

    static class DeckInfo32Cards_NoOrder implements DeckInfo {
        @Override
        public String getName() {
            return "32_CARDS";
        }
    }
}
