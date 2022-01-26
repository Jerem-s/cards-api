package fr.jeremy.cardsapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderValueIT extends AbstractIntegrationConfig {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_order_colors() throws Exception {
        // GIVEN
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        orderValueRequest.setValues(List.of("ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"));

        // WHEN
        ResultActions perform = this.mockMvc
                .perform(post("/order-values").content(objectMapper.writeValueAsString(orderValueRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // THEN
        perform.andExpect(status().isCreated());
    }

    @Test
    void should_get_order_colors() throws Exception {

        // WHEN
        ResultActions perform = this.mockMvc.perform(get("/order-values/last"));

        // THEN
        perform.andExpect(status().isOk());
    }
}
