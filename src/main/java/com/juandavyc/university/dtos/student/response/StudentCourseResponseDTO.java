package com.juandavyc.university.dtos.student.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class StudentCourseResponseDTO {

    private Long studentId;
    private Long courseId;
}
