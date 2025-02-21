package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.course.request.CourseUpdateDTO;
import com.juandavyc.university.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CourseUpdateMapper {

    void toUpdateCourseEntityFromDTO(
            CourseUpdateDTO courseUpdateDTO,
            @MappingTarget CourseEntity courseEntity
    );

}
