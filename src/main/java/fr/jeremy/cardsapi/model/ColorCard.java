package fr.jeremy.cardsapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ColorCard {

    @Id
    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderCard orderColor;


}
