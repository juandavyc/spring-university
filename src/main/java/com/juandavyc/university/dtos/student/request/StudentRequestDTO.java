package com.juandavyc.university.dtos.student.request;

import com.juandavyc.university.dtos.person.request.PersonRequestDTO;
import com.juandavyc.university.entities.PersonRoleEnum;
import jakarta.persistence.PrePersist;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class StudentRequestDTO {

    @NotNull(message = "Scholarship is required")
    private Boolean scholarship;

    @NotNull(message = "person is required")
    @Valid
    private PersonRequestDTO person;

    public void setPerson(PersonRequestDTO person) {
        if (person != null) {
            person.setRole(PersonRoleEnum.STUDENT);
        }
        this.person = person;
    }

}
