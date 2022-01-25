package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.mappers.OrderColorMapper;
import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.models.OrderColor;
import fr.jeremy.cardsapi.repositories.OrderColorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        OrderColorResponse orderColorResponseMapped = new OrderColorResponse();
        when(orderColorMapper.mapToEntity(orderColorRequest)).thenReturn(orderColor);
        when(orderColorRepository.save(orderColor)).thenReturn(orderColorSaved);
        when(orderColorMapper.mapToDto(orderColorSaved)).thenReturn(orderColorResponseMapped);

        // WHEN
        OrderColorResponse orderColorResponse = orderColorService.save(orderColorRequest);

        // THEN
        verify(orderColorMapper).mapToEntity(orderColorRequest);
        verify(orderColorRepository).save(orderColor);
        verify(orderColorMapper).mapToDto(orderColorSaved);
        assertThat(orderColorResponse).isEqualTo(orderColorResponseMapped);
    }

    @Test
    void should_find_last() {
        // GIVEN
        OrderColor orderColor = new OrderColor();
        when(orderColorRepository.findFirstByOrderByCreatedAtDesc()).thenReturn(Optional.of(orderColor));
        when(orderColorMapper.mapToDto(orderColor)).thenReturn(new OrderColorResponse());

        // WHEN
        OrderColorResponse orderColorResponse = orderColorService.findLast();

        // THEN
        verify(orderColorRepository).findFirstByOrderByCreatedAtDesc();
        verify(orderColorMapper).mapToDto(orderColor);
        assertThat(orderColorResponse).isNotNull();
    }

    @Test
    void should_find_last_even_if_no_data_in_repo() {
        // GIVEN
        when(orderColorRepository.findFirstByOrderByCreatedAtDesc()).thenReturn(Optional.empty());

        // WHEN
        OrderColorResponse orderColorResponse = orderColorService.findLast();

        // THEN
        verify(orderColorRepository).findFirstByOrderByCreatedAtDesc();
        assertThat(orderColorResponse).isNotNull();
        assertThat(orderColorResponse.getColors()).containsExactly("SPADES", "DIAMONDS", "HEARTS", "CLUBS");
    }
}
