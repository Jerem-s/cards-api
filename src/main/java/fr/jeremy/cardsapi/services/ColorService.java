package fr.jeremy.cardsapi.services;

import fr.jeremy.cardsapi.dto.response.ColorListResponse;
import fr.jeremy.cardsapi.models.ColorCard;
import fr.jeremy.cardsapi.repositories.ColorCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorService {

    private final ColorCardRepository colorCardRepository;

    public ColorService(ColorCardRepository colorCardRepository) {
        this.colorCardRepository = colorCardRepository;
    }

    public ColorListResponse findAll() {
        List<String> colors = colorCardRepository.findAll().stream().map(ColorCard::getColor)
                .collect(Collectors.toList());
        return new ColorListResponse(colors);
    }
}
