package fr.jeremy.cardsapi.mappers;

public interface Mapper<IN, OUT> {
    OUT map(IN in);
}
