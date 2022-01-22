package fr.jeremy.cardsapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class OrderCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderCard orderCard = (OrderCard) o;
        return Objects.equals(id, orderCard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
