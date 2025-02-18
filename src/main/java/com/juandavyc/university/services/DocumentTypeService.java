package com.juandavyc.university.services;

import com.juandavyc.university.dtos.document.request.DocumentTypeRequestDTO;
import com.juandavyc.university.dtos.document.response.DocumentTypeResponseDTO;
import com.juandavyc.university.dtos.document.response.DocumentTypeResponseWithPersonsDTO;
import com.juandavyc.university.entities.DocumentTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface DocumentTypeService {

    Page<DocumentTypeResponseDTO> findAll(Pageable pageable);

    Page<DocumentTypeResponseWithPersonsDTO> findAllWithPersons(Pageable pageable);

    Page<DocumentTypeResponseDTO> findByFilters(Long id, String name, Pageable pageable);

    DocumentTypeResponseDTO create(DocumentTypeRequestDTO documentTypeRequestDTO);

    DocumentTypeResponseDTO update(Long id,DocumentTypeRequestDTO documentTypeRequestDTO);

    void delete(Long id);
//    public DocumentTypeEntity partialUpdate(Long id, PartialDocumentUpdateDTO dto) {
//        DocumentTypeEntity document = findByIdOrThrow(id);
//
//        dto.getName().ifPresent(document::setName);
//        dto.getIsActive().ifPresent(document::setIsActive);
//
//        return documentTypeRepository.save(document);
//    }
//public List<DocumentTypeEntity> findByCriteria(DocumentTypeSearchCriteria criteria) {
//    Specification<DocumentTypeEntity> spec = Specification.where(null);
//
//    if (criteria.getName() != null) {
//        spec = spec.and(DocumentTypeSpecifications.hasName(criteria.getName()));
//    }
//
//    if (criteria.getIsActive() != null) {
//        spec = spec.and(DocumentTypeSpecifications.isActive(criteria.getIsActive()));
//    }
//    private Optional<String> description = Optional.empty();
//    private Optional<String> location  = Optional.empty();
//    return documentTypeRepository.findAll(spec);
//}


//    DocumentTypeEntity findById(Long id);
//    List<DocumentTypeEntity> findAll();
//    List<DocumentTypeEntity> findByName(String name);


}
