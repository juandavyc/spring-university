package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.classroom.request.ClassroomRequestDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface ClassroomUpdateMapper {

    @Mapping(target = "id", ignore = true)
    void toClassroomEntityFromDTO(
            ClassroomRequestDTO classroomRequestDTO,
            @MappingTarget ClassroomEntity classroomEntity

    );
}
