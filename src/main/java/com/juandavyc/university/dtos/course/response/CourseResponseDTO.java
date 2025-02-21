package com.juandavyc.university.dtos.course.response;

import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CourseResponseDTO {

    private Long id;
    private String name;
    private String timePeriod;

    private ClassroomResponseDTO classroom;

}
