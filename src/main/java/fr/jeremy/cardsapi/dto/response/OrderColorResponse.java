package fr.jeremy.cardsapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderColorResponse {
    private Long id;
    private List<String> colors;
}
