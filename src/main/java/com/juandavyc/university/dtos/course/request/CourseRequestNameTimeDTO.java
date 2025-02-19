package com.juandavyc.university.dtos.course.request;

import com.juandavyc.university.dtos.course.CourseResponseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CourseRequestNameTimeDTO{


    @NotBlank(message = "Name course cannot be blank")
    private String name;

    @NotNull(message = "Time course cannot be null")
    private Boolean time;


}
