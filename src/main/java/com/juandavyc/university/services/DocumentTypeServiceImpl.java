package com.juandavyc.university.services;

import com.juandavyc.university.dtos.document.request.DocumentTypeRequestDTO;
import com.juandavyc.university.dtos.document.response.DocumentTypeResponseDTO;
import com.juandavyc.university.dtos.document.response.DocumentTypeResponseWithPersonsDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.DocumentTypeEntity;
import com.juandavyc.university.mappers.DocumentTypeMapper;
import com.juandavyc.university.repositories.DocumentRepository;
import com.juandavyc.university.specifications.DocumentTypeSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentRepository documentRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Transactional(
            propagation = Propagation.NESTED,
            readOnly = true
    )
    @Override
    public Page<DocumentTypeResponseDTO> findAll(Pageable pageable) {

        final var documentTypes = findAllDocuments(pageable);
        return documentTypes.map(documentTypeMapper::toDocumentTypeResponseDTO);

    }

    @Transactional(
            propagation = Propagation.NESTED,
            readOnly = true
    )
    @Override
    public Page<DocumentTypeResponseWithPersonsDTO> findAllWithPersons(Pageable pageable) {

        final var documentTypes = findAllDocuments(pageable);
        return documentTypes.map(documentTypeMapper::toDocumentTypeResponseWithPersonsDTO);

    }

    @Override
    public Page<DocumentTypeResponseDTO> findByFilters(Long id, String name, Pageable pageable) {

        Specification<DocumentTypeEntity> specification = Specification
                .where(DocumentTypeSpecifications.hasName(name))
                .and(DocumentTypeSpecifications.hasId(id));

        final var documentTypes = documentRepository.findAll(specification, pageable);

        if (documentTypes.getContent().isEmpty()) {
            throw new IllegalArgumentException("Documents types not found");
        }
        return documentTypes.map(documentTypeMapper::toDocumentTypeResponseDTO);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public DocumentTypeResponseDTO create(DocumentTypeRequestDTO documentTypeRequestDTO) {

        if (documentRepository.existsByName(documentTypeRequestDTO.getName())) {
            throw new IllegalArgumentException("Document type with name:" + documentTypeRequestDTO.getName() + "  already exists");
        }
        try {
            final var toCreate = documentTypeMapper.toDocumentTypeEntity(documentTypeRequestDTO);

            return documentTypeMapper.toDocumentTypeResponseDTO(
                    documentRepository.save(toCreate)
            );
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Concurrent: Document type with name:" + documentTypeRequestDTO.getName() + "  already exists");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public DocumentTypeResponseDTO update(Long id, DocumentTypeRequestDTO documentTypeRequestDTO) {

        final var documentTypeEntity = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document type by id:" + id + ", not found"));

        // TODO: add validation by Name
        documentTypeEntity.setName(documentTypeRequestDTO.getName());

        try {
            return documentTypeMapper.toDocumentTypeResponseDTO(
                    documentRepository.save(documentTypeEntity)
            );
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Concurrent: Document type with name:" + documentTypeRequestDTO.getName() + "  already exists");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        if (documentRepository.existsById(id)) {
            try {
                documentRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new IllegalArgumentException("Concurrent: Document type with id:" + id + " cannot be deleted");
            }
        } else {
            throw new IllegalArgumentException("Document type by id:" + id + " not found");
        }
    }

    private Page<DocumentTypeEntity> findAllDocuments(Pageable pageable) {

        final var documentsTypes = documentRepository.findAll(pageable);
        if (documentsTypes.getContent().isEmpty()) {
            throw new IllegalArgumentException("Documents types not found");
        }
        return documentsTypes;

    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public DocumentTypeEntity findById(Long id) {
//        return documentRepository
//                .findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Document by id " + id + " not found"));
//    }
//
//    public List<DocumentTypeEntity> findAll() {
//        final var documentTypes = documentRepository.findAll();
//        if (documentTypes.isEmpty()){
//            throw new IllegalArgumentException("No documents found");
//        }
//        else{
//            return documentTypes;
//        }
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<DocumentTypeEntity> findByName(String name) {
//        final var documentTypes = documentRepository.findByNameStartsWith(name);
//        if (documentTypes.isEmpty()){
//            throw new IllegalArgumentException("Document by name " + name + " not found");
//        }
//        else{
//            return documentTypes;
//        }
//    }


}
