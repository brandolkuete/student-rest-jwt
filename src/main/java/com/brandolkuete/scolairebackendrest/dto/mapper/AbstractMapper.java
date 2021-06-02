package com.brandolkuete.scolairebackendrest.dto.mapper;

import com.brandolkuete.scolairebackendrest.dto.AuditableDTO;
import com.brandolkuete.scolairebackendrest.entities.Auditable;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractMapper<E extends Auditable,D extends AuditableDTO> {

    private static final ModelMapper modelMapper= new ModelMapper();

    public E toEntity(D dto){
        return modelMapper.map(dto, (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public D toDto(E entity){
        return modelMapper.map(entity, (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }
}
