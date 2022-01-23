package fr.jeremy.cardsapi.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "fr.jeremy.cardsapi.model")
public class EntityConfiguration {
}
