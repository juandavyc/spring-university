package com.juandavyc.university.mappers;

import com.juandavyc.university.dtos.document.request.DocumentTypeRequestDTO;
import com.juandavyc.university.dtos.document.response.DocumentTypeResponseDTO;
import com.juandavyc.university.dtos.document.response.DocumentTypeResponseWithPersonsDTO;
import com.juandavyc.university.entities.DocumentTypeEntity;
import org.mapstruct.Mapper;
import org.w3c.dom.DocumentType;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {

    DocumentTypeResponseDTO toDocumentTypeResponseDTO(DocumentTypeEntity documentTypeEntity);

    DocumentTypeResponseWithPersonsDTO toDocumentTypeResponseWithPersonsDTO(DocumentTypeEntity documentTypeEntity);
    DocumentTypeEntity toDocumentTypeEntity (DocumentTypeRequestDTO documentTypeRequestDTO);
}
