package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.CardDto;
import fr.jeremy.cardsapi.dto.mappers.CardMapper;
import fr.jeremy.cardsapi.dto.response.CardResponse;
import fr.jeremy.cardsapi.models.Card;
import fr.jeremy.cardsapi.repositories.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CardServiceTest {
    CardService cardService;
    private CardRepository cardRepository;
    private CardMapper cardMapper;

    @BeforeEach
    void setUp() {
        this.cardRepository = mock(CardRepository.class);
        this.cardMapper = mock(CardMapper.class);
        cardService = new CardService(cardRepository, cardMapper);
        when(cardRepository.count()).thenReturn(10L);
        when(cardRepository.findIdBy()).thenReturn(List.of(() -> 1L, () -> 2L, () -> 3L, () -> 4L, () -> 5L, () -> 6L,
                () -> 7L, () -> 8L, () -> 9L, () -> 10L));
        when(cardRepository.findById(anyLong())).thenAnswer(invocation -> {
            Long id = (Long) invocation.getArguments()[0];
            Card card = new Card();
            card.setId(id);
            return Optional.of(card);
        }

        );
        when(cardMapper.mapToDto(any(Card.class))).thenAnswer(invocation -> {
            Card card = (Card) invocation.getArguments()[0];
            CardDto cardDto = new CardDto();
            cardDto.setId(card.getId());
            return cardDto;
        });
    }

    @Test
    void should_not_have_duplicates() {

        // WHEN
        CardResponse hand = cardService.getHand();

        // THEN
        assertThat(hand.getCards()).doesNotHaveDuplicates();

    }

    @Test
    void should_have_10_cards() {

        // WHEN
        CardResponse hand = cardService.getHand();

        // THEN
        assertThat(hand.getCards()).hasSize(10);

    }
}
