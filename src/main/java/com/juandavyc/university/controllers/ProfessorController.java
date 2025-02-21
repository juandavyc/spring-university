package com.juandavyc.university.controllers;

import com.juandavyc.university.dtos.professor.request.ProfessorRequestDTO;
import com.juandavyc.university.dtos.professor.request.ProfessorUpdateDTO;
import com.juandavyc.university.dtos.professor.response.ProfessorResponseDTO;
import com.juandavyc.university.dtos.student.request.StudentRequestDTO;
import com.juandavyc.university.dtos.student.request.StudentUpdateDTO;
import com.juandavyc.university.dtos.student.response.StudentResponseDTO;
import com.juandavyc.university.entities.PersonRoleEnum;
import com.juandavyc.university.services.ProfessorService;
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

@Validated
@RestController
@RequestMapping(path = "api/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<Page<ProfessorResponseDTO>> findAll(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(professorService.findAll(pageable));
    }

    @GetMapping(path = "search")
    public ResponseEntity<Page<ProfessorResponseDTO>> findAll(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String salary,
            // person
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String document,
            // person -> document
            @RequestParam(required = false) Long documentTypeId,
            @RequestParam(required = false) String documentTypeName,
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                professorService.findByFilters(
                        id,
                        salary,
                        name,
                        document,
                        documentTypeId,
                        documentTypeName,
                        pageable
                )
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProfessorResponseDTO> findById(
          @PathVariable Long id
    ) {
        return ResponseEntity.ok(professorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> create(
            @Valid @RequestBody ProfessorRequestDTO professorRequestDTO
    ){

        return ResponseEntity.created(
                URI.create(
                        ("/").concat(
                                professorService.create(professorRequestDTO).getId().toString()
                        )
                )

        ).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ProfessorResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ProfessorUpdateDTO studentUpdateDTO
    ){
        return ResponseEntity.ok(professorService.update(id,studentUpdateDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }

//
//    @DeleteMapping(path = "{id}")
//    public ResponseEntity<Void> delete(
//            @PathVariable Long id
//    ){
//        studentService.delete(id);
//        return ResponseEntity.noContent().build();
//    }



}
