package com.juandavyc.university.dtos.classroom.response;


import com.juandavyc.university.dtos.course.response.CourseNotClassroomResponseDTO;
import com.juandavyc.university.dtos.course.response.CourseResponseDTO;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClassroomWithCoursesResponseDTO {

    private Long id;
    private Integer room;

    private List<CourseNotClassroomResponseDTO> courses;

}
