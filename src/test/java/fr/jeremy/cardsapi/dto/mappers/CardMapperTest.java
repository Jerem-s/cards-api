package fr.jeremy.cardsapi.dto.mappers;

import fr.jeremy.cardsapi.dto.CardDto;
import fr.jeremy.cardsapi.models.Card;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.models.ValueCard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardMapperTest {

    CardMapper cardMapper = new CardMapper();

    @Test
    void should_map_to_dto() {
        // GIVEN
        Card card = new Card();
        card.setId(1L);
        card.setColor(new ColorCard("SPADES"));
        card.setValue(new ValueCard("ACE"));

        // WHEN
        CardDto cardDto = cardMapper.mapToDto(card);

        // THEN
        assertThat(cardDto.getId()).isEqualTo(1L);
        assertThat(cardDto.getColor()).isEqualTo("SPADES");
        assertThat(cardDto.getValue()).isEqualTo("ACE");
    }
}
