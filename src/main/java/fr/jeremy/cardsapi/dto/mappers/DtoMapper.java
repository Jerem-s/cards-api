package fr.jeremy.cardsapi.dto.mappers;

public interface DtoMapper<ENTITY, DTO> {
    DTO mapToDto(ENTITY entity);
}
