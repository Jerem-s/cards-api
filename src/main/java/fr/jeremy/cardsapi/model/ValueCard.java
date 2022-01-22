package fr.jeremy.cardsapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ValueCard {
    @Id
    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "order_card_id")
    private OrderCard orderCard;

}
