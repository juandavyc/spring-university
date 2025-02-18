package com.juandavyc.university.dtos.person.response;

import com.juandavyc.university.dtos.document.response.DocumentTypeResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PersonWithDocumentTypeResponseDTO {

    private Long id;
    private String name;
    private String document;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DocumentTypeResponseDTO documentType;

}
