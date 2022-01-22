package fr.jeremy.cardsapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "fr.jeremy.cardsapi.repository")
public class RepositoryConfiguration {
}
