package com.juandavyc.university.dtos.professor.response;

import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor

public class ProfessorResponseDTO {

    private Long id;
    private Integer salary;
    private PersonResponseDTO person;

}
