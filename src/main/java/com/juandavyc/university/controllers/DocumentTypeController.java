package com.juandavyc.university.controllers;

import com.juandavyc.university.dtos.document.request.DocumentTypeRequestDTO;
import com.juandavyc.university.dtos.document.response.DocumentTypeResponseDTO;
import com.juandavyc.university.dtos.document.response.DocumentTypeResponseWithPersonsDTO;
import com.juandavyc.university.services.DocumentTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "api/documents-types")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @GetMapping
    public ResponseEntity<Page<DocumentTypeResponseDTO>> findAll(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(documentTypeService.findAll(pageable));
    }

    @GetMapping(path = "search")
    public ResponseEntity<Page<DocumentTypeResponseDTO>> findByFilters(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(documentTypeService.findByFilters(id, name, pageable));
    }

    @PostMapping
    public ResponseEntity<String> create(
            @Valid @RequestBody DocumentTypeRequestDTO documentTypeRequestDTO
    ) {
        return ResponseEntity.created(
                URI.create(
                        ("/").concat(documentTypeService.create(documentTypeRequestDTO).getId().toString())
                )
        ).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<DocumentTypeResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DocumentTypeRequestDTO documentTypeRequestDTO
    ) {
        return ResponseEntity.ok(documentTypeService.update(id,documentTypeRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id
    ) {
        documentTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
