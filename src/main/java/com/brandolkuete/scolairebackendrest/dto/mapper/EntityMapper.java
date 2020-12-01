package com.brandolkuete.scolairebackendrest.dto.mapper;

import java.text.ParseException;
import java.util.List;

public interface EntityMapper<Entity,DTO> {
    Entity toEntity(DTO dto) throws ParseException;
    DTO toDto(Entity entity);
    List<DTO> toEntity(List<Entity> entities);
    List<Entity> toDto(List<DTO> dtos);
}
