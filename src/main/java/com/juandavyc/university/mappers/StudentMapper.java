package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.student.request.StudentRequestDTO;
import com.juandavyc.university.dtos.student.response.StudentResponseDTO;
import com.juandavyc.university.entities.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // response
    StudentResponseDTO toStudentResponseDTO(StudentEntity studentEntity);

    //request
    StudentEntity toStudentEntity(StudentRequestDTO studentRequestDTO);

}
