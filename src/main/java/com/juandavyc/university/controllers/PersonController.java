package com.juandavyc.university.controllers;

import com.juandavyc.university.dtos.person.request.PersonRequestDTO;
import com.juandavyc.university.dtos.person.request.PersonUpdateDTO;
import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import com.juandavyc.university.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;

@Validated
@RestController
@RequestMapping(path = "api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    // my presence is a gift

    @GetMapping
    public ResponseEntity<Page<PersonResponseDTO>> findAll(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(personService.findAll(pageable));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PersonResponseDTO> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(personService.findById(id));
    }


    @GetMapping(path = "search")
    public ResponseEntity<Page<PersonResponseDTO>> findByFilters(
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

    @PostMapping
    public ResponseEntity<String> create(
            @Valid @RequestBody PersonRequestDTO personRequestDTO
    ) {
        throw new UnsupportedOperationException("Cannot create a person directly. Use Professor or Student instead.");
//        return ResponseEntity.created(
//                URI.create(
//                        ("/").concat(
//                                personService.create(personRequestDTO).getId().toString()
//                        )
//                )
//
//        ).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PersonResponseDTO> updated(
            @PathVariable Long id,
            @Valid @RequestBody PersonUpdateDTO personUpdateDTO
    ) {

        return ResponseEntity.ok(
                personService.update(id, personUpdateDTO)
        );
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
