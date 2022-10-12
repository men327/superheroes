package com.factech.colombia.superheroes.mappers;

import com.factech.colombia.superheroes.dtos.SuperheroeDTO;
import com.factech.colombia.superheroes.entities.SuperheroeEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SuperheroeMapper {

    @Mapping(source = "activo", target = "estado")
    SuperheroeDTO toDto(SuperheroeEntity entity);

    @Mapping(source = "estado", target = "activo")
    SuperheroeEntity toEntity(SuperheroeDTO dto);

}
