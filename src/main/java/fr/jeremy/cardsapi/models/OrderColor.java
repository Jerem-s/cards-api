package fr.jeremy.cardsapi.models;

import lombok.Getter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class OrderColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "order_card_color_card", joinColumns = @JoinColumn(name = "order_card_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "color_card_color", referencedColumnName = "color"))
    private Set<ColorCard> colorCards = new LinkedHashSet<>();

    public void setColorCards(List<ColorCard> colorCards) {
        this.colorCards.clear();
        this.colorCards.addAll(colorCards);
    }

}
