package fr.jeremy.cardsapi.dto.mappers;

public interface EntityMapper<DTO, ENTITY> {
    ENTITY mapToEntity(DTO dto);
}
