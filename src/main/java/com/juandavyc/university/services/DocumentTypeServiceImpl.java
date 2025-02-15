package com.juandavyc.university.services;

import com.juandavyc.university.entities.DocumentTypeEntity;
import com.juandavyc.university.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

@Slf4j
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentRepository documentRepository;


    @Transactional(readOnly = true)
    @Override
    public DocumentTypeEntity findById(Long id) {
        return documentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document by id " + id + " not found"));
    }

    public List<DocumentTypeEntity> findAll() {
        final var documentTypes = documentRepository.findAll();
        if (documentTypes.isEmpty()){
            throw new IllegalArgumentException("No documents found");
        }
        else{
            return documentTypes;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentTypeEntity> findByName(String name) {
        final var documentTypes = documentRepository.findByNameStartsWith(name);
        if (documentTypes.isEmpty()){
            throw new IllegalArgumentException("Document by name " + name + " not found");
        }
        else{
            return documentTypes;
        }
    }


}
