package com.juandavyc.university.dtos.course.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CourseRequestDTO {

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotNull(message = "timePeriod cannot be blank")
    private Boolean timePeriod;

    @Min(value = 1)
    @NotNull(message = "idDocument cannot be null")
    private Long classroomId;

}
