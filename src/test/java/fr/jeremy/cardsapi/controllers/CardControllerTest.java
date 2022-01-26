package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.CardDto;
import fr.jeremy.cardsapi.dto.response.CardResponse;
import fr.jeremy.cardsapi.services.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
@AutoConfigureMockMvc
class CardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    void should_retrieve_10_cards() throws Exception {
        // GIVEN
        CardResponse cardResponse = new CardResponse();

        cardResponse.setCards(Set.of(new CardDto(1L, "ACE", "SPADES"), new CardDto(2L, "ACE", "SPADES"),
                new CardDto(3L, "ACE", "SPADES"), new CardDto(4L, "ACE", "SPADES"), new CardDto(5L, "ACE", "SPADES"),
                new CardDto(6L, "ACE", "SPADES"), new CardDto(7L, "ACE", "SPADES"), new CardDto(8L, "ACE", "SPADES"),
                new CardDto(9L, "ACE", "SPADES"), new CardDto(10L, "ACE", "SPADES")));
        when(cardService.getHand()).thenReturn(cardResponse);

        // WHEN
        ResultActions perform = this.mockMvc.perform(get("/cards"));

        // THEN
        perform.andExpect(status().isOk()).andExpect(jsonPath("$.cards.length()").value(10))
                .andExpect(jsonPath("$.cards[0].id").isNumber()).andExpect(jsonPath("$.cards[0].color").isString())
                .andExpect(jsonPath("$.cards[0].value").isString());
    }
}
