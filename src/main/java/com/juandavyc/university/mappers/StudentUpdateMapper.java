package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.student.request.StudentUpdateDTO;
import com.juandavyc.university.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StudentUpdateMapper {

    void toUpdateStudentEntityFromDTO(
            StudentUpdateDTO studentUpdateDTO,
            @MappingTarget StudentEntity studentEntity
    );

}
