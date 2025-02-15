package com.juandavyc.university.dtos.classroom.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClassroomResponseDTO {

    private Integer id;
    private Integer room;

}
