package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.classroom.request.ClassroomRequestDTO;
import com.juandavyc.university.dtos.classroom.request.ClassroomWithCoursesRequestDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

// mapeo simple sin default
//@Mapper(uses = {CourseMapper.class})
@Mapper(componentModel = "spring", uses = {CourseMapper.class})

public interface ClassroomMapper {

    //@Mapping(source = "room", target = "salon")
    //ClassroomResponseDTO toClassroomResponseDTO(ClassroomEntity classroomEntity);

    // response
    //@Mapping(source = "version", target = "version")

    // request
    ClassroomEntity toClassroomEntityFromDTO(ClassroomRequestDTO classroomRequestDTO);

    // response
    ClassroomResponseDTO toClassroomResponseDTO(ClassroomEntity classroomEntity);

    ClassroomWithCoursesResponseDTO toClassroomWithCoursesResponseDTO(ClassroomEntity classroomEntity);



    //ClassroomEntity toClassroomEntity(ClassroomRequestDTO classroomRequestDTO);

    //@Mapping(target = "version", ignore = true)
    //ClassroomEntity updateEntityFromDTO(ClassroomRequestDTO classroomRequestDTO, @MappingTarget ClassroomEntity classroomEntity);

    //@Mapping(source = "courses", target = "courses")
    //ClassroomEntity toClassroomWithCoursesEntity(ClassroomWithCoursesRequestDTO classroomWithCoursesRequestDTO);

}
