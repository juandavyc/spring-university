package com.juandavyc.university.dtos.student.response;

import com.juandavyc.university.dtos.course.response.CourseResponseDTO;
import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StudentCoursesResponseDTO {

    private Long id;
    private Boolean scholarship;

    //private PersonResponseDTO person;
    private List<CourseResponseDTO> courses;
}

