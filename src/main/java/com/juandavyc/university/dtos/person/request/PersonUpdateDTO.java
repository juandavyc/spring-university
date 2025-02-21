package com.juandavyc.university.dtos.person.request;

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

import java.util.Optional;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class PersonUpdateDTO {

    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ]*$")
    private String name;

    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[0-9]*$")
    private String document;

    @Min(value = 1)
    private Long idDocument;

    private PersonRoleEnum role;

}
