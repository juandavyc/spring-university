package com.juandavyc.university.dtos.course.response;

import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CourseNotClassroomResponseDTO {

    private Long id;
    private String name;
    private String timePeriod;


}
