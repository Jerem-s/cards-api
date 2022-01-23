package fr.jeremy.cardsapi.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "fr.jeremy.cardsapi.repository")
public class RepositoryConfiguration {
}
