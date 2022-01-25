package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.models.OrderColor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderColorRepositoryTest {
    @Autowired
    private OrderColorRepository orderColorRepository;

    @Test
    void should_retrieve_colors_in_same_order() {
        // GIVEN
        OrderColor entity = new OrderColor();
        List<ColorCard> colorCard = new ArrayList<>();
        ColorCard spades = new ColorCard();
        spades.setColor("SPADES");
        ColorCard diamonds = new ColorCard();
        diamonds.setColor("DIAMONDS");
        ColorCard hearts = new ColorCard();
        hearts.setColor("HEARTS");
        ColorCard clubs = new ColorCard();
        clubs.setColor("CLUBS");
        colorCard.add(spades);
        colorCard.add(hearts);
        colorCard.add(diamonds);
        colorCard.add(clubs);

        entity.setColorCards(colorCard);
        entity.setCreatedAt(ZonedDateTime.now());
        orderColorRepository.save(entity);

        //WHEN
        Optional<OrderColor> result = orderColorRepository.findFirstByOrderByCreatedAtDesc();

        //THEN
        assertThat(result.get().getColorCards()).containsExactly(spades, hearts, diamonds, clubs);
    }

    @Test
    void should_retrieve_last_record() {
        // GIVEN
        OrderColor orderColor1 = new OrderColor();
        orderColor1.setColorCards(List.of());
        orderColor1.setCreatedAt(ZonedDateTime.now());
        orderColorRepository.save(orderColor1);

        OrderColor orderColor2 = new OrderColor();
        orderColor2.setColorCards(List.of());
        orderColor2.setCreatedAt(ZonedDateTime.now().plusDays(1));
        orderColorRepository.save(orderColor2);

        //WHEN
        Optional<OrderColor> result = orderColorRepository.findFirstByOrderByCreatedAtDesc();

        //THEN
        assertThat(result.get().getCreatedAt()).isAfter(orderColor1.getCreatedAt());

    }
}
