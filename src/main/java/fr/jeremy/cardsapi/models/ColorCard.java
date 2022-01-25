package fr.jeremy.cardsapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColorCard {

    @Id
    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "order_color_id")
    private OrderColor orderColor;

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
