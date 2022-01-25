package fr.jeremy.cardsapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.services.OrderColorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class OrderColorIT extends AbstractIntegrationConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_order_colors() throws Exception {
        // GIVEN
        OrderColorRequest orderColorRequest = new OrderColorRequest();
        orderColorRequest.setColors(List.of("SPADES", "DIAMONDS", "HEARTS", "CLUBS"));

        // WHEN
        ResultActions perform = this.mockMvc
                .perform(post("/order-colors").content(objectMapper.writeValueAsString(orderColorRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // THEN
        perform.andExpect(status().isCreated());
    }

}
