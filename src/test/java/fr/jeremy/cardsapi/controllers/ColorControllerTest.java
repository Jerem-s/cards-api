package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.response.ColorListResponse;
import fr.jeremy.cardsapi.services.ColorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ColorController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ColorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ColorService colorService;

    @BeforeEach
    void setUp() {
        when(colorService.findAll()).thenReturn(new ColorListResponse(List.of("SPADES", "DIAMONDS")));
    }

    @Test
    void should_retrieve_list_of_colors() throws Exception {
        mockMvc.perform(get("/colors")).andExpect(status().isOk()).andExpect(jsonPath("$.colors[0]").value("SPADES"))
                .andExpect(jsonPath("$.colors[1]").value("DIAMONDS"));
    }
}
