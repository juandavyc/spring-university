package com.juandavyc.university.dtos.document.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeRequestDTO {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String name;

}
