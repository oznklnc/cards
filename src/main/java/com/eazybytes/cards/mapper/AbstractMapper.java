package com.eazybytes.cards.mapper;

public abstract class AbstractMapper<E, D> {

    public abstract D toDto(E entity);

    public abstract E toEntity(D dto);

    public abstract E toEntity(D dto, E entity);
}
