package fr.jeremy.cardsapi.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class OrderCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "order_card_color_card",
            joinColumns = @JoinColumn(name = "order_card_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "color_card_color", referencedColumnName = "color"))
    private Set<ColorCard> colorCards = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "order_card_value_card",
            joinColumns = @JoinColumn(name = "order_card_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "value_card_value", referencedColumnName = "value"))
    private Set<ValueCard> valueCards = new LinkedHashSet<>();

    public void setColorCards(List<ColorCard> colorCards) {
        this.colorCards.clear();
        this.colorCards.addAll(colorCards);
    }

}
