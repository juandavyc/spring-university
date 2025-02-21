package com.juandavyc.university.dtos.person.request;

import com.juandavyc.university.dtos.document.request.DocumentTypeRequestDTO;
import com.juandavyc.university.entities.DocumentTypeEntity;
import com.juandavyc.university.entities.PersonRoleEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class PersonRequestDTO {

    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ]*$")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[0-9]*$")
    @NotBlank(message = "document cannot be blank")
    private String document;

    @Min(value = 1)
    @NotNull(message = "idDocument cannot be null")
    private Long idDocument;

    @NotNull(message = "Role cannot be null")
    private PersonRoleEnum role;

}
