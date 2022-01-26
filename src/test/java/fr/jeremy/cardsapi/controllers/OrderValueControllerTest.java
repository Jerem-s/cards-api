package fr.jeremy.cardsapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.models.ValueCard;
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

@WebMvcTest(OrderValueController.class)
@AutoConfigureMockMvc
class OrderValueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_order_values() throws Exception {
        // GIVEN
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        orderValueRequest.setValues(List.of("ACE", "TWO", "THREE", "FOUR"));

        OrderValueResponse orderValueResponse = new OrderValueResponse();
        orderValueResponse.setValues(List.of(new ValueCard("ACE", 1), new ValueCard("TWO", 2),
                new ValueCard("THREE", 3), new ValueCard("FOUR", 4)));

        when(orderService.saveValues(any(OrderValueRequest.class))).thenReturn(orderValueResponse);

        // WHEN
        ResultActions perform = this.mockMvc
                .perform(post("/order-values").content(objectMapper.writeValueAsString(orderValueRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(jsonPath("$.values[0].value").value("ACE"))
                .andExpect(jsonPath("$.values[1].value").value("TWO"))
                .andExpect(jsonPath("$.values[2].value").value("THREE"))
                .andExpect(jsonPath("$.values[3].value").value("FOUR"));
    }

    @Test
    void should_get_order_values() throws Exception {
        // GIVEN
        OrderValueResponse orderValueResponse = new OrderValueResponse();
        orderValueResponse.setValues(List.of(new ValueCard("ACE", 1), new ValueCard("TWO", 2),
                new ValueCard("THREE", 3), new ValueCard("FOUR", 4)));

        when(orderService.getOrderValues()).thenReturn(orderValueResponse);

        // WHEN
        ResultActions perform = this.mockMvc.perform(get("/order-values"));

        // THEN
        perform.andExpect(status().isOk()).andExpect(jsonPath("$.values[0].value").value("ACE"))
                .andExpect(jsonPath("$.values[1].value").value("TWO"))
                .andExpect(jsonPath("$.values[2].value").value("THREE"))
                .andExpect(jsonPath("$.values[3].value").value("FOUR"));
    }
}
