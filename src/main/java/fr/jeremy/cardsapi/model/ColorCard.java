package fr.jeremy.cardsapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ColorCard {

    @Id
    @Column(name = "color")
    private String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColorCard colorCard = (ColorCard) o;
        return Objects.equals(color, colorCard.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
