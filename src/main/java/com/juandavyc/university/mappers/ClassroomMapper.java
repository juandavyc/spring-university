package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.classroom.request.ClassroomRequestDTO;
import com.juandavyc.university.dtos.classroom.request.ClassroomWithCoursesRequestDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.dtos.course.CourseResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;

import com.juandavyc.university.entities.CourseEntity;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// mapeo simple sin default
//@Mapper(uses = {CourseMapper.class})
@Mapper(componentModel = "spring", uses = {CourseMapper.class})

public interface ClassroomMapper {


    //ClassroomMapper INSTANCE = Mappers.getMapper(ClassroomMapper.class);

    //@Mapping(source = "room", target = "salon")
    //ClassroomResponseDTO toClassroomResponseDTO(ClassroomEntity classroomEntity);

    // @Mapping(source = "courses", target = "courses")

    ClassroomResponseDTO toClassroomResponseDTO(ClassroomEntity classroomEntity);

    ClassroomWithCoursesResponseDTO toClassroomWithCoursesResponseDTO(ClassroomEntity classroomEntity);

    ClassroomEntity toClassroomEntity(ClassroomRequestDTO classroomRequestDTO);

    @Mapping(source = "courses", target = "courses")
    ClassroomEntity toClassroomWithCoursesEntity(ClassroomWithCoursesRequestDTO classroomWithCoursesRequestDTO);

}
