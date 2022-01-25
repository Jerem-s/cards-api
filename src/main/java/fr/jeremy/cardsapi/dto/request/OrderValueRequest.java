package fr.jeremy.cardsapi.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderValueRequest {
    private List<String> values;
}
