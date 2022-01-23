package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.response.ColorListResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.repositories.ColorCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ColorServiceTest {

    ColorService colorService;

    @BeforeEach
    void setUp() {
        ColorCardRepository colorCardRepository = mock(ColorCardRepository.class);
        colorService = new ColorService(colorCardRepository);
        List<ColorCard> colorCards = List.of(new ColorCard("SPADES"), new ColorCard("DIAMONDS"));
        when(colorCardRepository.findAll()).thenReturn(colorCards);
    }

    @Test
    void should_return_list_of_colors() {

        // WHEN
        ColorListResponse colors = colorService.findAll();

        // THEN
        assertThat(colors.getColors()).containsExactly("SPADES", "DIAMONDS");
    }
}
