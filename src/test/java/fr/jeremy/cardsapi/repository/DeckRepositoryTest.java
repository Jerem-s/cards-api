package fr.jeremy.cardsapi.repository;

import fr.jeremy.cardsapi.model.ColorCard;
import fr.jeremy.cardsapi.model.Deck;
import fr.jeremy.cardsapi.model.OrderCard;
import fr.jeremy.cardsapi.repository.projections.DeckInfo;
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

        //WHEN
        DeckInfo deckInfo = deckRepository.findOrderColorCardsByName("32_CARDS");

        //THEN
        assertThat(deckInfo.getName()).isEqualTo("32_CARDS");
    }

    @Test
    void should_retrieve_order_colors() {
        //GIVEN
        OrderCard orderCard = new OrderCard();
        orderCard.setColorCards(List.of(new ColorCard("SPADES"), new ColorCard("DIAMONDS")));
        OrderCard save = orderCardRepository.save(orderCard);
        Deck deck1 = deckRepository.findById(1L)
                .map(deck -> {
                    deck.setOrder(save);
                    return deck;
                }).get();
        deckRepository.save(deck1);

        //WHEN
        DeckInfo deckInfo = deckRepository.findOrderColorCardsByName(deck1.getName());

        //THEN
        assertThat(deckInfo.getOrder().getColorCards()).containsExactly(new ColorCard("SPADES"), new ColorCard("DIAMONDS"));

    }

    @Test
    void should_return_empty_set() {
        //WHEN
        DeckInfo deckInfo = deckRepository.findOrderColorCardsByName("52_CARDS");

        //THEN
        assertThat(deckInfo.getOrder()).isNotNull();
        assertThat(deckInfo.getOrder().getColorCards()).isEmpty();
    }
}
