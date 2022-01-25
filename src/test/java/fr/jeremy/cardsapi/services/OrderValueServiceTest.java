package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.mappers.OrderValueMapper;
import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.models.OrderValue;
import fr.jeremy.cardsapi.repositories.OrderValueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrderValueServiceTest {
    private OrderValueService orderValueService;
    private OrderValueRepository orderValueRepository;
    private OrderValueMapper orderValueMapper;

    @BeforeEach
    void setUp() {
        this.orderValueRepository = mock(OrderValueRepository.class);
        this.orderValueMapper = mock(OrderValueMapper.class);
        this.orderValueService = new OrderValueService(orderValueRepository, orderValueMapper);

    }

    @Test
    void should_call_repository_save() {
        // GIVEN
        OrderValueRequest orderValueRequest = new OrderValueRequest();
        OrderValue orderValue = new OrderValue();
        OrderValue orderValueSaved = new OrderValue();
        OrderValueResponse orderValueResponseMapped = new OrderValueResponse();
        when(orderValueMapper.mapToEntity(orderValueRequest)).thenReturn(orderValue);
        when(orderValueRepository.save(orderValue)).thenReturn(orderValueSaved);
        when(orderValueMapper.mapToDto(orderValueSaved)).thenReturn(orderValueResponseMapped);

        // WHEN
        OrderValueResponse orderValueResponse = orderValueService.save(orderValueRequest);

        // THEN
        verify(orderValueMapper).mapToEntity(orderValueRequest);
        verify(orderValueRepository).save(orderValue);
        verify(orderValueMapper).mapToDto(orderValueSaved);
        assertThat(orderValueResponse).isEqualTo(orderValueResponseMapped);
    }

    @Test
    void should_find_last() {
        // GIVEN
        OrderValue orderValue = new OrderValue();
        when(orderValueRepository.findFirstByOrderByCreatedAtDesc()).thenReturn(Optional.of(orderValue));
        when(orderValueMapper.mapToDto(orderValue)).thenReturn(new OrderValueResponse());

        // WHEN
        OrderValueResponse orderValueResponse = orderValueService.findLast();

        // THEN
        verify(orderValueRepository).findFirstByOrderByCreatedAtDesc();
        verify(orderValueMapper).mapToDto(orderValue);
        assertThat(orderValueResponse).isNotNull();
    }

    @Test
    void should_find_last_even_if_no_data_in_repo() {
        // GIVEN
        when(orderValueRepository.findFirstByOrderByCreatedAtDesc()).thenReturn(Optional.empty());

        // WHEN
        OrderValueResponse orderValueResponse = orderValueService.findLast();

        // THEN
        verify(orderValueRepository).findFirstByOrderByCreatedAtDesc();
        assertThat(orderValueResponse).isNotNull();
        assertThat(orderValueResponse.getValues()).containsExactly("ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING");
    }

}
