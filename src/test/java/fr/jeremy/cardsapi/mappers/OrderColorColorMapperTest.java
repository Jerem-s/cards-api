package fr.jeremy.cardsapi.mappers;

import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.repositories.projections.OrderColorCardsInfo;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OrderColorColorMapperTest {

    OrderCardColorMapper orderCardColorMapper = new OrderCardColorMapper();

    @Test
    void should_map() {
        // GIVEN
        OrderColorCardsInfo orderColorCardsInfo = new OrderColorCardsInfo() {
            @Override
            public String getName() {
                return "32_CARDS";
            }

            @Override
            public Optional<OrderCardInfo> getOrderColor() {
                return Optional.empty();
            }
        };
        // WHEN
        OrderColorResponse orderColorResponse = orderCardColorMapper.map(orderColorCardsInfo);

        // THEN
        assertThat(orderColorResponse.getDeckName()).isEqualTo("32_CARDS");
        assertThat(orderColorResponse.getColorsOrder()).isEmpty();
    }

    @Test
    void should_map_colors() {
        // GIVEN
        OrderColorCardsInfo orderColorCardsInfo = new OrderColorCardsInfo() {
            @Override
            public String getName() {
                return "32_CARDS";
            }

            @Override
            public Optional<OrderCardInfo> getOrderColor() {
                return Optional.of(() -> {
                    LinkedHashSet<ColorCard> colorCards = new LinkedHashSet<>();
                    colorCards.add(new ColorCard("SPADES"));
                    colorCards.add(new ColorCard("DIAMONDS"));
                    return colorCards;
                });
            }
        };

        // WHEN
        OrderColorResponse orderColorResponse = orderCardColorMapper.map(orderColorCardsInfo);

        // THEN
        assertThat(orderColorResponse.getDeckName()).isEqualTo("32_CARDS");
        assertThat(orderColorResponse.getColorsOrder()).containsExactly("SPADES", "DIAMONDS");
    }
}
