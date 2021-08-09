package com.brandolkuete.scolairebackendrest.service;

import com.brandolkuete.scolairebackendrest.dto.AuditableDTO;
import com.brandolkuete.scolairebackendrest.dto.mapper.AbstractMapper;
import com.brandolkuete.scolairebackendrest.entities.Auditable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractService<E extends Auditable,D extends AuditableDTO>  {

    protected abstract JpaRepository<E,Long> getRepository();
    protected abstract AbstractMapper<E,D> getMapper();

    /*public D save(D entityDTO){
        final E entity = getMapper().toEntity(entityDTO);
        return getMapper().toDto((E) getRepository().save(entity));
    }*/
    public E save(D entityDTO){
        final E entity = getMapper().toEntity(entityDTO);
        return (E) getRepository().save(entity);
    }

    public List<D> findAll() {
        return getRepository().findAll().stream().map(getMapper()::toDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        E entity = (E) Optional.of(getRepository().findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get).get();
        getRepository().deleteById(id);
    }

    public D getOne(Long id) {
        return Optional.of(getRepository().findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(getMapper()::toDto)
                .get();
    }
}
