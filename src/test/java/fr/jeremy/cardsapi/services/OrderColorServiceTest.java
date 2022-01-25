package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.mappers.OrderColorMapper;
import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.models.OrderColor;
import fr.jeremy.cardsapi.repositories.OrderColorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class OrderColorServiceTest {
    private OrderColorService orderColorService;
    private OrderColorRepository orderColorRepository;
    private OrderColorMapper orderColorMapper;

    @BeforeEach
    void setUp() {
        this.orderColorRepository = mock(OrderColorRepository.class);
        this.orderColorMapper = mock(OrderColorMapper.class);
        this.orderColorService = new OrderColorService(orderColorRepository, orderColorMapper);

    }

    @Test
    void should_call_repository_save() {
        // GIVEN
        OrderColorRequest orderColorRequest = new OrderColorRequest();
        OrderColor orderColor = new OrderColor();
        OrderColor orderColorSaved = new OrderColor();
        when(orderColorMapper.mapToEntity(orderColorRequest)).thenReturn(orderColor);
        when(orderColorRepository.save(orderColor)).thenReturn(orderColorSaved);

        // WHEN
        OrderColorResponse save = orderColorService.save(orderColorRequest);

        // THEN
        verify(orderColorMapper).mapToEntity(orderColorRequest);
        verify(orderColorRepository).save(orderColor);
        verify(orderColorMapper).mapToDto(orderColorSaved);
    }
}
