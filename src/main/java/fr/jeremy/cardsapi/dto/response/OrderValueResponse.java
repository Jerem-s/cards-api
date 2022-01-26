package fr.jeremy.cardsapi.dto.response;

import fr.jeremy.cardsapi.models.ValueCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderValueResponse {
    private List<ValueCard> values;
}
