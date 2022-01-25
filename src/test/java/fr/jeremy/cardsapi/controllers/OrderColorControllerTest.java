package fr.jeremy.cardsapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.services.OrderColorService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderColorController.class)
@AutoConfigureMockMvc
class OrderColorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderColorService orderColorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_order_colors() throws Exception {
        // GIVEN
        OrderColorRequest orderColorRequest = new OrderColorRequest();
        orderColorRequest.setColors(List.of("SPADES", "DIAMONDS", "HEARTS", "CLUBS"));

        OrderColorResponse orderColorResponse = new OrderColorResponse();
        orderColorResponse.setId(1L);
        orderColorResponse.setColors(List.of("SPADES", "DIAMONDS", "HEARTS", "CLUBS"));

        when(orderColorService.save(any(OrderColorRequest.class))).thenReturn(orderColorResponse);

        // WHEN
        ResultActions perform = this.mockMvc
                .perform(post("/order-colors").content(objectMapper.writeValueAsString(orderColorRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.colors[0]").value("SPADES")).andExpect(jsonPath("$.colors[1]").value("DIAMONDS"))
                .andExpect(jsonPath("$.colors[2]").value("HEARTS")).andExpect(jsonPath("$.colors[3]").value("CLUBS"));
    }
}
