package fr.jeremy.cardsapi.dto.mappers;

import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.models.OrderValue;
import fr.jeremy.cardsapi.models.ValueCard;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;

class OrderValueMapperTest {

    OrderValueMapper mapper = new OrderValueMapper();

    @Test
    void should_map_to_entity() {
        // GIVEN
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        orderValueRequest.setValues(List.of("ACE"));

        // WHEN
        OrderValue orderValue = mapper.mapToEntity(orderValueRequest);

        // THEN
        assertThat(orderValue.getValueCards()).containsExactly(new ValueCard("ACE"));
        assertThat(orderValue.getCreatedAt()).isCloseTo(ZonedDateTime.now(), byLessThan(1, SECONDS));
    }

    @Test
    void should_map_to_dto() {
        // GIVEN
        OrderValue orderValue = new OrderValue();
        orderValue.setId(1L);
        orderValue.setValueCards(List.of(new ValueCard("ACE")));

        // WHEN
        OrderValueResponse orderValueResponse = mapper.mapToDto(orderValue);

        // THEN
        assertThat(orderValueResponse.getValues()).containsExactly("ACE");
        assertThat(orderValueResponse.getId()).isEqualTo(1L);
    }
}
