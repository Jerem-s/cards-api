package fr.jeremy.cardsapi.dto.mappers;

import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.models.OrderColor;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;

class OrderColorMapperTest {

    OrderColorMapper mapper = new OrderColorMapper();

    @Test
    void should_map_to_entity() {
        // GIVEN
        OrderColorRequest orderColorRequest = new OrderColorRequest();
        orderColorRequest.setColors(List.of("SPADES"));

        // WHEN
        OrderColor orderColor = mapper.mapToEntity(orderColorRequest);

        // THEN
        assertThat(orderColor.getColorCards()).containsExactly(new ColorCard("SPADES"));
        assertThat(orderColor.getCreatedAt()).isCloseTo(ZonedDateTime.now(), byLessThan(1, SECONDS));
    }

    @Test
    void should_map_to_dto() {
        // GIVEN
        OrderColor orderColor = new OrderColor();
        orderColor.setId(1L);
        orderColor.setColorCards(List.of(new ColorCard("SPADES")));

        // WHEN
        OrderColorResponse orderColorResponse = mapper.mapToDto(orderColor);

        // THEN
        assertThat(orderColorResponse.getColors()).containsExactly("SPADES");
        assertThat(orderColorResponse.getId()).isEqualTo(1L);
    }
}
