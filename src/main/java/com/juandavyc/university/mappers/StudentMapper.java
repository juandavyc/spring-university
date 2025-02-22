package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.student.request.StudentRequestDTO;

import com.juandavyc.university.dtos.student.response.StudentCoursesResponseDTO;
import com.juandavyc.university.dtos.student.response.StudentResponseDTO;
import com.juandavyc.university.entities.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {CourseMapper.class}
)
public interface StudentMapper {

    // response
    StudentResponseDTO toStudentResponseDTO(StudentEntity studentEntity);

    StudentCoursesResponseDTO toStudentCoursesResponseDTO(StudentEntity studentEntity);



    //request
    StudentEntity toStudentEntity(StudentRequestDTO studentRequestDTO);

}
