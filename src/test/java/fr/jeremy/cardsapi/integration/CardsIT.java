package fr.jeremy.cardsapi.integration;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CardsIT extends AbstractIntegrationConfig {
    @Test
    void should_get_hand() throws Exception {

        // WHEN
        ResultActions perform = this.mockMvc.perform(get("/cards"));

        // THEN
        perform.andExpect(status().isOk());
    }
}
