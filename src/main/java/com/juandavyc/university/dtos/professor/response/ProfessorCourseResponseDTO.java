package com.juandavyc.university.dtos.professor.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor

public class ProfessorCourseResponseDTO {

    private Long professorId;
    private Long courseId;

}
