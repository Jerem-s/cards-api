package fr.jeremy.cardsapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "fr.jeremy.cardsapi.model")
public class EntityConfiguration {
}
