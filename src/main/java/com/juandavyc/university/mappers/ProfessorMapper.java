package com.juandavyc.university.mappers;


import com.juandavyc.university.dtos.person.request.PersonRequestDTO;
import com.juandavyc.university.dtos.professor.request.ProfessorRequestDTO;
import com.juandavyc.university.dtos.professor.response.ProfessorResponseDTO;
import com.juandavyc.university.entities.PersonEntity;
import com.juandavyc.university.entities.ProfessorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    // response
    //   @Mapping(source = "person.id", target = "person.id", qualifiedByName = "hashId")
    ProfessorResponseDTO toProfessorResponseDTO(ProfessorEntity professorEntity);

    // request
    ProfessorEntity toProfessorEntity(ProfessorRequestDTO professorRequestDTO);

    // update




//    @Named("hashId")
//    default Long hashId(Long professorId) {
//        return professorId + 123456789;
//    }
}
