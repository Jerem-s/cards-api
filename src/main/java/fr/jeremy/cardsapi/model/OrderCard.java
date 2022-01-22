package fr.jeremy.cardsapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class OrderCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "orderColor")
    private List<ColorCard> colors = new ArrayList<>();

    @OneToMany(mappedBy = "orderCard")
    private List<ValueCard> values = new ArrayList<>();

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
