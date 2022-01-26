package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.ValueCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ValueCardRepositoryTest {

    @Autowired
    private ValueCardRepository valueCardRepository;

    @Test
    void should_retrieve_values_in_same_order() {
        // GIVEN

        ValueCard ace = new ValueCard("ACE", 1);
        ValueCard two = new ValueCard("TWO", 2);
        ValueCard three = new ValueCard("THREE", 3);
        ValueCard four = new ValueCard("FOUR", 5);
        ValueCard five = new ValueCard("FIVE", 6);
        ValueCard six = new ValueCard("SIX", 7);
        ValueCard seven = new ValueCard("SEVEN", 8);
        ValueCard eight = new ValueCard("EIGHT", 9);
        ValueCard nine = new ValueCard("NINE", 10);
        ValueCard ten = new ValueCard("TEN", 11);
        ValueCard jack = new ValueCard("JACK", 12);
        ValueCard queen = new ValueCard("QUEEN", 13);
        ValueCard king = new ValueCard("KING", 14);
        List<ValueCard> valueCards = List.of(ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king);

        valueCardRepository.saveAll(valueCards);

        // WHEN
        List<ValueCard> result = valueCardRepository.findByOrderByRankAsc();

        // THEN
        assertThat(result).containsExactly(ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king);
    }

}
