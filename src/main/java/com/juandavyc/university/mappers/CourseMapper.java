package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.course.CourseResponseDTO;
import com.juandavyc.university.entities.CourseEntity;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    //CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "time", target = "timePeriod", qualifiedByName = "mapTimePeriod")
    CourseResponseDTO toCourseResponseDTO(CourseEntity courseEntity);

    @Named("mapTimePeriod")
    default String time(boolean time) {
        return time ? "AM" : "PM";
    }

}
