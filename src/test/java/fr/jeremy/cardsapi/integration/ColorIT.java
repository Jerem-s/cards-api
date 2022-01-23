package fr.jeremy.cardsapi.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ColorIT extends AbstractIntegrationConfig {

    @SneakyThrows
    @Test
    void should_retrieve_colors() {
        // WHEN
        this.mockMvc.perform(get("/colors")).andExpect(status().isOk());
    }
}
