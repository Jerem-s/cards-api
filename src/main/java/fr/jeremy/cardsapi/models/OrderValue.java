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
public class OrderValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "orderValue", orphanRemoval = true)
    private Set<ValueCard> valueCard = new LinkedHashSet<>();

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setValueCard(Set<ValueCard> valueCard) {
        this.valueCard = valueCard;
    }

}
