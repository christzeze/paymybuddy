package com.openclassroooms.paymybuddy.mapper;

import java.util.Collection;
import java.util.List;

public interface AbstractMapper<E, D> {
    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(Collection<D> dtos);

    List<D> toDTOs(Collection<E> entities);
}
