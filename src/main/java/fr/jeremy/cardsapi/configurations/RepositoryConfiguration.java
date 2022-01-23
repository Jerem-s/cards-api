package fr.jeremy.cardsapi.configurations;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "fr.jeremy.cardsapi.repositories")
public class RepositoryConfiguration {
}
