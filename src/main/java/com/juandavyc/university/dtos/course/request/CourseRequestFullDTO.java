package com.juandavyc.university.dtos.course.request;

import com.juandavyc.university.dtos.course.CourseResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CourseRequestFullDTO extends CourseResponseDTO {


    @NotBlank(message = "Name course cannot be blank")
    private String name;

    @NotNull(message = "Time course cannot be null")
    private Boolean time;


}
