package com.juandavyc.university.dtos.document.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DocumentTypeResponseDTO {

    private Long id;
    private String name;
    //private Short version;

}
