package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.ColorCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ColorCardRepositoryTest {
    @Autowired
    private ColorCardRepository colorCardRepository;

    @Test
    void should_retrieve_colors_in_same_order() {
        // GIVEN

        ColorCard spades = new ColorCard("SPADES", 1);
        ColorCard diamonds = new ColorCard("DIAMONDS", 2);
        ColorCard hearts = new ColorCard("HEARTS", 3);
        ColorCard clubs = new ColorCard("CLUBS", 4);
        List<ColorCard> colorCards = List.of(spades, diamonds, hearts, clubs);

        colorCardRepository.saveAll(colorCards);

        // WHEN
        List<ColorCard> result = colorCardRepository.findByOrderByRankAsc();

        // THEN
        assertThat(result).containsExactly(spades, diamonds, hearts, clubs);
    }

}
