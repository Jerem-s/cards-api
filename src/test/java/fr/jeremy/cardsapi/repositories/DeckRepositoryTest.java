package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.models.Deck;
import fr.jeremy.cardsapi.models.OrderCard;
import fr.jeremy.cardsapi.repositories.projections.OrderColorCardsInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class DeckRepositoryTest {
    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private OrderCardRepository orderCardRepository;

    @Test
    void should_retrieve_deck_info() {

        // WHEN
        OrderColorCardsInfo orderColorCardsInfo = deckRepository.findByName("32_CARDS");

        // THEN
        assertThat(orderColorCardsInfo.getName()).isEqualTo("32_CARDS");
        assertThat(orderColorCardsInfo.getOrder()).isNotPresent();

    }

    @Test
    void should_retrieve_order_colors() {
        // GIVEN
        OrderCard orderCard = new OrderCard();
        orderCard.setColorCards(List.of(new ColorCard("SPADES"), new ColorCard("DIAMONDS")));
        OrderCard save = orderCardRepository.save(orderCard);
        Deck deck1 = deckRepository.findById(1L).map(deck -> {
            deck.setOrder(save);
            return deck;
        }).get();
        deckRepository.save(deck1);

        // WHEN
        OrderColorCardsInfo orderColorCardsInfo = deckRepository.findByName(deck1.getName());

        // THEN
        assertThat(orderColorCardsInfo.getOrder()).isPresent();
        assertThat(orderColorCardsInfo.getOrder().get().getColorCards()).containsExactly(new ColorCard("SPADES"),
                new ColorCard("DIAMONDS"));

    }
}
