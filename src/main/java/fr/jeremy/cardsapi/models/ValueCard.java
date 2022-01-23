package fr.jeremy.cardsapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ValueCard {
    @Id
    @Column(name = "value")
    private String value;

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
