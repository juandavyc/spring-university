package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import com.juandavyc.university.dtos.person.response.PersonWithDocumentTypeResponseDTO;
import com.juandavyc.university.entities.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonResponseDTO toPersonResponseDTO(PersonEntity personEntity);
    PersonWithDocumentTypeResponseDTO toPersonWithDocumentTypeResponseDTO(PersonEntity personEntity);

}
