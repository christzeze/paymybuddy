package com.openclassroooms.paymybuddy.mapper;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstratMapper<E, D> {
    public abstract E toEntity(D dto);
    public abstract D toDTO(E entity);

    public List<E> toEntities(Collection<D> dtos) {
        return CollectionUtils.isEmpty(dtos) ? Collections.emptyList() : dtos.stream().map(this::toEntity).collect(Collectors.toList());

    }

    public List<D> toDTOs(Collection<E> entities) {
        return CollectionUtils.isEmpty(entities) ? Collections.emptyList() : entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
