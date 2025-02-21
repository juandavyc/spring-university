package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.professor.request.ProfessorUpdateDTO;
import com.juandavyc.university.entities.ProfessorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProfessorUpdateMapper {

    @Mapping(target = "id", ignore = true)
    void toUpdateProfessorEntityFromDTO(
            ProfessorUpdateDTO professorUpdateDTO,
            @MappingTarget ProfessorEntity professorEntity
    );
}