package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.OrderValue;
import fr.jeremy.cardsapi.models.ValueCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderValueRepositoryTest {

    @Autowired
    private OrderValueRepository orderValueRepository;

    @Test
    void should_retrieve_colors_in_same_order() {
        // GIVEN
        OrderValue entity = new OrderValue();
        List<ValueCard> valueCards = new ArrayList<>();
        ValueCard ace = new ValueCard();
        ace.setValue("ACE");
        ValueCard two = new ValueCard();
        two.setValue("TWO");
        ValueCard four = new ValueCard();
        four.setValue("FOUR");
        ValueCard seven = new ValueCard();
        seven.setValue("SEVEN");
        valueCards.add(ace);
        valueCards.add(four);
        valueCards.add(two);
        valueCards.add(seven);

        entity.setValueCards(valueCards);
        entity.setCreatedAt(ZonedDateTime.now());
        orderValueRepository.save(entity);

        // WHEN
        Optional<OrderValue> result = orderValueRepository.findFirstByOrderByCreatedAtDesc();

        // THEN
        assertThat(result.get().getValueCards()).containsExactly(ace, four, two, seven);
    }

    @Test
    void should_retrieve_last_record() {
        // GIVEN
        OrderValue orderValue1 = new OrderValue();
        orderValue1.setValueCards(List.of());
        orderValue1.setCreatedAt(ZonedDateTime.now());
        orderValueRepository.save(orderValue1);
        OrderValue orderValue2 = new OrderValue();
        orderValue2.setValueCards(List.of());
        orderValue2.setCreatedAt(ZonedDateTime.now().plusDays(1));
        orderValueRepository.save(orderValue2);

        // WHEN
        Optional<OrderValue> result = orderValueRepository.findFirstByOrderByCreatedAtDesc();

        // THEN
        assertThat(result.get().getCreatedAt()).isAfter(orderValue1.getCreatedAt());

    }
}
