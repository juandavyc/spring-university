package com.juandavyc.university.dtos.document.response;

import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DocumentTypeResponseWithPersonsDTO {

    private Long id;
    private String name;
    private List<PersonResponseDTO> persons;

}
