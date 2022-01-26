package fr.jeremy.cardsapi.dto.response;

import fr.jeremy.cardsapi.models.ColorCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderColorResponse {
    private List<ColorCard> colors;
}
