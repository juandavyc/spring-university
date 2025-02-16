package com.juandavyc.university.dtos.classroom.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClassroomRequestDTO {

    @Min(value = 100, message = "Room must be greater than or equal to 100")
    @Max(value = 509, message = "Room must be less than or equal to 509")
    @Digits(integer = 3, fraction = 0, message = "Room must be exactly 3 digits")
    //@Pattern(regexp = "^[1-5]0[1-9]$", message = "Room number must follow pattern 1-5,0,1-9")
    protected Integer room;

}
