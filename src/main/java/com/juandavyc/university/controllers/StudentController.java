package com.juandavyc.university.controllers;

import com.juandavyc.university.dtos.student.request.StudentRequestDTO;
import com.juandavyc.university.dtos.student.request.StudentUpdateDTO;

import com.juandavyc.university.dtos.student.response.StudentCourseResponseDTO;
import com.juandavyc.university.dtos.student.response.StudentCoursesResponseDTO;
import com.juandavyc.university.dtos.student.response.StudentResponseDTO;

import com.juandavyc.university.services.StudentService;
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
@RequestMapping(path = "api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<StudentResponseDTO>> findByFilters(

            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Boolean scholarship,
            // person
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String document,
            // person -> document
            @RequestParam(required = false) Long documentTypeId,
            @RequestParam(required = false) String documentTypeName,

            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                studentService.findByFilters(
                        id,
                        scholarship,
                        name,
                        document,
                        documentTypeId,
                        documentTypeName,
                        pageable
                )
        );
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<StudentCoursesResponseDTO> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(
            @Valid @RequestBody StudentRequestDTO studentRequestDTO
    ) {

        return ResponseEntity.created(
                URI.create(
                        ("/").concat(
                                studentService.create(studentRequestDTO).getId().toString()
                        )
                )

        ).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<StudentResponseDTO> create(
            @PathVariable Long id,
            @RequestBody @Valid StudentUpdateDTO studentUpdateDTO
    ) {
        return ResponseEntity.ok(studentService.update(id, studentUpdateDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Student -> course

    @GetMapping(path = "{studentId}/courses")
    public ResponseEntity<StudentCoursesResponseDTO> findByIdCourses(
            @PathVariable Long studentId,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(
                studentService.findByIdCourses(
                        studentId,
                        name
                )
        );
    }


    @PostMapping(path = "{studentId}/courses/{courseId}")
    public ResponseEntity<StudentCourseResponseDTO> addStudentCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(
                studentService.addStudentCourse(
                        studentId, courseId
                )
        );
    }

    @DeleteMapping(path = "{studentId}/courses/{courseId}")
    public ResponseEntity<Void> removeStudentCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        studentService.removeStudentCourse(studentId,courseId);

        return ResponseEntity.noContent().build();
    }



}
