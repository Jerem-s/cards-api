package fr.jeremy.cardsapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "orderValue", orphanRemoval = true)
    private List<ValueCard> valueCards = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;


    @Override
    public String toString() {
        return "OrderValue{" +
                "id=" + id +
                ", valueCards=" + valueCards +
                ", createdAt=" + createdAt +
                '}';
    }
}
