package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.person.request.PersonRequestDTO;
import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import com.juandavyc.university.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    // responses
    PersonResponseDTO toPersonResponseDTO(PersonEntity personEntity);

    // request
    PersonEntity toPersonEntity(PersonRequestDTO personRequestDTO);



}
