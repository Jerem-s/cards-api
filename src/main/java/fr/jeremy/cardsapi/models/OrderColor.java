package fr.jeremy.cardsapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class OrderColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany
    private Set<ColorCard> colorCard = new LinkedHashSet<>();

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    public void setColorCard(Set<ColorCard> colorCard) {
        this.colorCard = colorCard;
    }
}
