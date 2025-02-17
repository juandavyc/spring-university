package com.juandavyc.university.dtos.course.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CourseRequestDTO {


    @NotBlank(message = "Id course cannot be blank")
    protected Long id;


}
