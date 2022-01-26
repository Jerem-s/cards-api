package fr.jeremy.cardsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Long id;
    private String color;
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardDto cardDto = (CardDto) o;
        return Objects.equals(id, cardDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
