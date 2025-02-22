package com.juandavyc.university.dtos.professor.response;

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

public class ProfessorCoursesResponseDTO {

    private Long id;
    private Integer salary;

    private List<CourseResponseDTO> courses;

}


