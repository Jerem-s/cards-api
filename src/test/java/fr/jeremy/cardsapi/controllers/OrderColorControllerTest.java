package fr.jeremy.cardsapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderColorController.class)
@AutoConfigureMockMvc
class OrderColorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_order_colors() throws Exception {
        // GIVEN
        OrderColorRequest orderColorRequest = new OrderColorRequest();
        orderColorRequest.setColors(List.of("SPADES", "DIAMONDS", "HEARTS", "CLUBS"));

        OrderColorResponse orderColorResponse = new OrderColorResponse();
        orderColorResponse.setColors(List.of(new ColorCard("SPADES", 1), new ColorCard("DIAMONDS", 2),
                new ColorCard("HEARTS", 3), new ColorCard("CLUBS", 4)));

        when(orderService.saveColors(any(OrderColorRequest.class))).thenReturn(orderColorResponse);

        // WHEN
        ResultActions perform = this.mockMvc
                .perform(post("/order-colors").content(objectMapper.writeValueAsString(orderColorRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(jsonPath("$.colors[0].color").value("SPADES"))
                .andExpect(jsonPath("$.colors[1].color").value("DIAMONDS"))
                .andExpect(jsonPath("$.colors[2].color").value("HEARTS"))
                .andExpect(jsonPath("$.colors[3].color").value("CLUBS"));
    }

    @Test
    void should_get_order_colors() throws Exception {
        // GIVEN
        OrderColorResponse orderColorResponse = new OrderColorResponse();
        orderColorResponse.setColors(List.of(new ColorCard("ACE", 1), new ColorCard("TWO", 2),
                new ColorCard("THREE", 3), new ColorCard("FOUR", 4)));

        when(orderService.getOrderColors()).thenReturn(orderColorResponse);

        // WHEN
        ResultActions perform = this.mockMvc.perform(get("/order-colors"));

        // THEN
        perform.andExpect(status().isOk()).andExpect(jsonPath("$.colors[0].color").value("ACE"))
                .andExpect(jsonPath("$.colors[1].color").value("TWO"))
                .andExpect(jsonPath("$.colors[2].color").value("THREE"))
                .andExpect(jsonPath("$.colors[3].color").value("FOUR"));
    }
}
