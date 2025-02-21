package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.person.request.PersonUpdateDTO;
import com.juandavyc.university.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PersonUpdateMapper {

    @Mapping(target = "id", ignore = true)
    void toUpdatePersonEntityFromDTO(PersonUpdateDTO personUpdateDTO, @MappingTarget PersonEntity personEntity);

}
