package com.juandavyc.university.dtos.classroom.response;


import com.juandavyc.university.dtos.course.CourseResponseDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClassroomWithCoursesResponseDTO {

    private Integer id;
    private Integer room;
    private List<CourseResponseDTO> courses;

}
