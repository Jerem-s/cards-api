package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void should_return_empty_set_of_colors() throws Exception {
        //GIVEN
        OrderColorResponse orderColorResponse = new OrderColorResponse();
        orderColorResponse.setDeckName("32_CARDS");
        orderColorResponse.setColorsOrder(List.of());
        when(orderService.findOrderColorByDeckName("32_CARDS")).thenReturn(orderColorResponse);

        //WHEN
        mockMvc.perform(get("/orders/32_CARDS/colors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deckName").value("32_CARDS"))
                .andExpect(jsonPath("$.colorsOrder").isEmpty());
    }

    @Test
    void should_return_set_of_colors() throws Exception {
        //GIVEN
        OrderColorResponse orderColorResponse = new OrderColorResponse();
        orderColorResponse.setDeckName("32_CARDS");
        orderColorResponse.setColorsOrder(List.of("SPADES", "DIAMONDS"));
        when(orderService.findOrderColorByDeckName("32_CARDS")).thenReturn(orderColorResponse);

        //WHEN
        mockMvc.perform(get("/orders/32_CARDS/colors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deckName").value("32_CARDS"))
                .andExpect(jsonPath("$.colorsOrder[0]").value("SPADES"))
                .andExpect(jsonPath("$.colorsOrder[1]").value("DIAMONDS"));
    }
}
