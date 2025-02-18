package com.juandavyc.university.controllers;

import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import com.juandavyc.university.dtos.person.response.PersonWithDocumentTypeResponseDTO;
import com.juandavyc.university.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Validated
@RestController
@RequestMapping(path = "api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    // my presence is a gift

    @GetMapping
    public ResponseEntity<Page<PersonWithDocumentTypeResponseDTO>> findAll(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(personService.findAll(pageable));
    }

    @GetMapping(path = "search")
    public ResponseEntity<Page<PersonWithDocumentTypeResponseDTO>> findByFilters(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String document,
            @RequestParam(required = false) LocalDate createdAt,
            @RequestParam(required = false) LocalDate updatedAt,
            @RequestParam(required = false) Long documentTypeId,
            @RequestParam(required = false) String documentTypeName,
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                personService.findByFilters(id, name, document, createdAt, updatedAt, documentTypeId, documentTypeName, pageable)
        );
    }

}
