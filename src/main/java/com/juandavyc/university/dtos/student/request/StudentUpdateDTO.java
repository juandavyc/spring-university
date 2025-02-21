package com.juandavyc.university.dtos.student.request;

import com.juandavyc.university.dtos.person.request.PersonRequestDTO;
import com.juandavyc.university.dtos.person.request.PersonUpdateDTO;
import com.juandavyc.university.entities.PersonRoleEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDTO {

    //@NotNull(message = "Scholarship is required")
    private Boolean scholarship;

    //@NotNull(message = "Person is required")
    @Valid
    private PersonUpdateDTO person;

    public void setPerson(PersonUpdateDTO person) {
       if(person != null) {
           this.person.setRole(PersonRoleEnum.STUDENT);
       }
       this.person = person;
    }
}
