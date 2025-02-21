package com.juandavyc.university.validators;

import com.juandavyc.university.entities.PersonEntity;
import com.juandavyc.university.repositories.PersonRepository;
import com.juandavyc.university.services.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor

public class PersonValidator {

    private final PersonRepository personRepository;


    public void existsByDocument(String documentRequest) {

        Optional.ofNullable(documentRequest)
                //.filter(document -> document.length() > 3)
                .ifPresentOrElse(
                        personRepository::existsByDocument,
                        ()-> {
                            throw new IllegalArgumentException("Document: " + documentRequest + ", already exists");
                        }
                );
    }

    public void validateDocumentUniqueness(String document, String entityDocument) {

        Optional.ofNullable(document)
                .filter(doc -> !doc.equals(entityDocument))
                .ifPresent(doc -> {
                    if (personRepository.existsByDocument(document)) {
                        throw new IllegalArgumentException("Document: " + document + ", already exists");
                    }
                });
    }

    public Boolean isDifferentIdDocument(Long idDocument, Long entityIdDocument) {

        return Optional.ofNullable(idDocument)
                .map(id -> !id.equals(entityIdDocument))
                .orElse(false);

    }

//    public Boolean existsById(Long idToValidate) {
//        return Optional.ofNullable(idToValidate)
//                .filter(id->id>0)
//                .map(personRepository::existsById)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + idToValidate));
//
//    }


}
