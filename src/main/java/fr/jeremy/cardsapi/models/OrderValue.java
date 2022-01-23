package fr.jeremy.cardsapi.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class OrderValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "order_card_value_card", joinColumns = @JoinColumn(name = "order_card_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "value_card_value", referencedColumnName = "value"))
    private Set<ValueCard> valueCards = new LinkedHashSet<>();
}
