package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.course.request.CourseRequestDTO;
import com.juandavyc.university.dtos.course.response.CourseNotClassroomResponseDTO;
import com.juandavyc.university.dtos.course.response.CourseResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    //CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);co


    @Mapping(source = "timePeriod", target = "time")
    CourseEntity toCourseEntity(CourseRequestDTO courseRequestDTO);


    @Mapping(source = "time", target = "timePeriod", qualifiedByName = "mapTimePeriod")
    @Mapping(source = "classroom", target = "classroom")
    CourseResponseDTO toCourseResponseDTO(CourseEntity courseEntity);

    @Mapping(source = "time", target = "timePeriod", qualifiedByName = "mapTimePeriod")
   // @Mapping(source = "timePeriod", target = "time")
    CourseNotClassroomResponseDTO  toCourseNotClassroomResponseDTO(CourseEntity courseEntity);

    // CourseEntity toCourseEntity(CourseRequestDTO courseRequestDTO);
    // CourseEntity toCourseEntity(CourseRequestNameTimeDTO courseRequestDTO);

    @Named("mapTimePeriod")
    default String time(boolean time) {
        return time ? "AM" : "PM";
    }

}
