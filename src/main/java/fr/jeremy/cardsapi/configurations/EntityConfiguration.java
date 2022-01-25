package fr.jeremy.cardsapi.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "fr.jeremy.cardsapi.models")
public class EntityConfiguration {
}
