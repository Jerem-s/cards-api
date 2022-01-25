package fr.jeremy.cardsapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.services.OrderValueService;
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
    private OrderValueService orderValueService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_order_values() throws Exception {
        // GIVEN
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        orderValueRequest.setValues(List.of("ACE", "KING", "TEN", "FIVE"));

        OrderValueResponse orderValueResponse = new OrderValueResponse();
        orderValueResponse.setId(1L);
        orderValueResponse.setValues(List.of("ACE", "KING", "TEN", "FIVE"));

        when(orderValueService.save(any(OrderValueRequest.class))).thenReturn(orderValueResponse);

        // WHEN
        ResultActions perform = this.mockMvc
                .perform(post("/order-values").content(objectMapper.writeValueAsString(orderValueRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // THEN
        perform.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.values[0]").value("ACE")).andExpect(jsonPath("$.values[1]").value("KING"))
                .andExpect(jsonPath("$.values[2]").value("TEN")).andExpect(jsonPath("$.values[3]").value("FIVE"));
    }

    @Test
    void should_get_last_order_values() throws Exception {
        // GIVEN
        OrderValueResponse orderValueResponse = new OrderValueResponse();
        orderValueResponse.setId(1L);
        orderValueResponse.setValues(List.of("ACE", "KING", "TEN", "FIVE"));

        when(orderValueService.findLast()).thenReturn(orderValueResponse);

        // WHEN
        ResultActions perform = this.mockMvc.perform(get("/order-values/last"));

        // THEN
        perform.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.values[0]").value("ACE")).andExpect(jsonPath("$.values[1]").value("KING"))
                .andExpect(jsonPath("$.values[2]").value("TEN")).andExpect(jsonPath("$.values[3]").value("FIVE"));
    }
}
