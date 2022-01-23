package fr.jeremy.cardsapi.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
public class OrderCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "order_card_color_card",
            joinColumns = @JoinColumn(name = "order_card_id"),
            inverseJoinColumns = @JoinColumn(name = "color_card_color"))
    private Set<ColorCard> colorCard = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "order_card_value_card",
            joinColumns = @JoinColumn(name = "order_card_id"),
            inverseJoinColumns = @JoinColumn(name = "value_card_value"))
    private Set<ValueCard> valueCard = new LinkedHashSet<>();

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
