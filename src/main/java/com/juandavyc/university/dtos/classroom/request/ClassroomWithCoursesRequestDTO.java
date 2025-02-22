package com.juandavyc.university.dtos.classroom.request;

import com.juandavyc.university.dtos.course.request.CourseRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;


@Data
//@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class ClassroomWithCoursesRequestDTO{

    @NotNull(message = "Course list cannot be null")
    @NotEmpty(message = "Course list cannot be empty")
    @Size(min = 1, max = 10, message = "Course list must have between 1 and 10 items")
    @Valid
    private List<@Valid CourseRequestDTO> courses;

}
