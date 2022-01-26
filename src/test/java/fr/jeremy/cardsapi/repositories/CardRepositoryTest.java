package fr.jeremy.cardsapi.repositories;

import fr.jeremy.cardsapi.repositories.projections.CardId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Test
    void should_retrieve_all_ids() {
        // WHEN
        List<CardId> idBy = this.cardRepository.findIdBy();

        // THEN
        long count = this.cardRepository.count();
        assertThat(idBy).hasSize((int) count);
    }
}
