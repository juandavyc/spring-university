package com.juandavyc.university.dtos.professor.request;

import com.juandavyc.university.dtos.person.request.PersonRequestDTO;
import com.juandavyc.university.dtos.person.request.PersonUpdateDTO;
import com.juandavyc.university.entities.PersonRoleEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProfessorUpdateDTO {

    // @NotNull(message = "Salary is required")
    private Integer salary;

    //@NotNull(message = "person is required")
    @Valid
    private PersonUpdateDTO person;

    public void setPerson(PersonUpdateDTO person) {
        if (person != null) {
            person.setRole(PersonRoleEnum.PROFESSOR);
        }
        this.person = person;
    }
}
