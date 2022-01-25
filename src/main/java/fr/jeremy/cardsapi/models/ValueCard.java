package fr.jeremy.cardsapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ValueCard {
    @Id
    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "order_value_id")
    private OrderValue orderValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValueCard valueCard = (ValueCard) o;
        return Objects.equals(value, valueCard.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
