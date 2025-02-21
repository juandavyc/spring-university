package com.juandavyc.university.dtos.student.response;

import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StudentResponseDTO {

    private Long id;
    private Boolean scholarship;

    private PersonResponseDTO person;

}

